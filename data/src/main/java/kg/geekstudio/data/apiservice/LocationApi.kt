package kg.geekstudio.data.apiservice

import kg.geekstudio.data.dtos.LocationDto
import kg.geekstudio.data.dtos.ResponseRickAndMorty
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApi {

    @GET("location")
    suspend fun fetchLocation(
        @Query("page")
        page:Int,
        @Query("name")
        name: String?
    ) : ResponseRickAndMorty<LocationDto>
}