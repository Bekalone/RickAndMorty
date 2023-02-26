package kg.geekstudio.domain.repositories

import androidx.paging.PagingData
import kg.geekstudio.domain.model.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {

    fun fetchEpisode(name:String?) : Flow<PagingData<Episode>>
}