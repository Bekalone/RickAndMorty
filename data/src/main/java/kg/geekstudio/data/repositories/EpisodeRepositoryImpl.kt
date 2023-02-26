package kg.geekstudio.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kg.geekstudio.data.apiservice.EpisodeApi
import kg.geekstudio.data.pagingsources.CharacterPagingSource
import kg.geekstudio.data.pagingsources.EpisodePagingSource
import kg.geekstudio.domain.model.Episode
import kg.geekstudio.domain.repositories.EpisodeRepository
import kotlinx.coroutines.flow.Flow

class EpisodeRepositoryImpl(private val episodeApi: EpisodeApi) : EpisodeRepository {

    override fun fetchEpisode(name: String?): Flow<PagingData<Episode>> {
        return Pager(config = PagingConfig(pageSize = 20,
            prefetchDistance = 20,
            enablePlaceholders = true,
            initialLoadSize = 20,
            maxSize = Int.MAX_VALUE,
            jumpThreshold = Int.MIN_VALUE),
            pagingSourceFactory = {
                EpisodePagingSource(episodeApi, name)
            }).flow
    }
}