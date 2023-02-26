package kg.geekstudio.data.pagingsources

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kg.geekstudio.data.apiservice.CharacterApi
import kg.geekstudio.data.dtos.toDomain
import kg.geekstudio.domain.model.Character
import retrofit2.HttpException
import java.io.IOException

class CharacterPagingSource(
    private val characterApi: CharacterApi,
    private val name:String?,
    private val status:String?,
    private val species:String?,
    private val gender:String?
): PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: 1

        return try {
            val response = characterApi.fetchCharacter(page,name, status,species, gender)
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