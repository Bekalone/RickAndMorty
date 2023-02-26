package kg.geekstudio.domain.repositories

import androidx.paging.PagingData
import kg.geekstudio.domain.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    fun fetchLocation(name:String?) : Flow<PagingData<Location>>
}