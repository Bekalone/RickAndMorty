package kg.geekstudio.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kg.geekstudio.data.apiservice.LocationApi
import kg.geekstudio.data.pagingsources.CharacterPagingSource
import kg.geekstudio.data.pagingsources.LocationPagingSource
import kg.geekstudio.domain.model.Location
import kg.geekstudio.domain.repositories.LocationRepository
import kotlinx.coroutines.flow.Flow

class LocationRepositoryImpl(private val locationApi: LocationApi):LocationRepository {

    override fun fetchLocation(name: String?): Flow<PagingData<Location>> {
        return Pager(config = PagingConfig(pageSize = 20,
            prefetchDistance = 20,
            enablePlaceholders = true,
            initialLoadSize = 20,
            maxSize = Int.MAX_VALUE,
            jumpThreshold = Int.MIN_VALUE),
            pagingSourceFactory = {
                LocationPagingSource(locationApi, name)
            }).flow
    }
}