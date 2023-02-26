package kg.geekstudio.data.apiservice

import kg.geekstudio.data.dtos.EpisodeDto
import kg.geekstudio.data.dtos.ResponseRickAndMorty
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodeApi {

    @GET("episode")
    suspend fun fetchEpisode(
        @Query("page")
        page:Int,
        @Query("name")
        name: String?,
    ) : ResponseRickAndMorty<EpisodeDto>
}