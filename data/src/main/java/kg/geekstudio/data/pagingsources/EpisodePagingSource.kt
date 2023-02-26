package kg.geekstudio.data.pagingsources

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kg.geekstudio.data.apiservice.CharacterApi
import kg.geekstudio.data.apiservice.EpisodeApi
import kg.geekstudio.data.dtos.toDomain
import kg.geekstudio.domain.model.Character
import kg.geekstudio.domain.model.Episode
import retrofit2.HttpException
import java.io.IOException

class EpisodePagingSource(
    private val episodeApi: EpisodeApi,
    private val name:String?
): PagingSource<Int, Episode>() {

    override fun getRefreshKey(state: PagingState<Int, Episode>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Episode> {
        val page = params.key ?: 1

        return try {
            val response = episodeApi.fetchEpisode(page,name)
            val nextKey = if (response.info.next == null) {
                null
            }else
                Uri.parse(response.info.next).getQueryParameter("page")?.toInt()
            LoadResult.Page(
                data = response.results.map { it.toDomain() },
                prevKey = null,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}