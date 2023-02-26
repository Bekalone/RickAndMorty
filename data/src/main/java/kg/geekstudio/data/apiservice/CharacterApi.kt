package kg.geekstudio.data.apiservice

import kg.geekstudio.data.dtos.CharacterDto
import kg.geekstudio.data.dtos.ResponseRickAndMorty
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {

    @GET("character")
    suspend fun fetchCharacter(
        @Query("page") page:Int,
        @Query("name")
        name: String?,
        @Query("status")
        status: String?,
        @Query("species")
        species: String?,
        @Query("gender")
        gender: String?,
    ) : ResponseRickAndMorty<CharacterDto>
}