package kg.geekstudio.data.dtos

import com.google.gson.annotations.SerializedName
import kg.geekstudio.domain.model.Character

data class ResponseRickAndMorty<T>(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<T>,
)

data class Info(
    @SerializedName("count")
    val count: Int, // 826
    @SerializedName("pages")
    val pages: Int, // 42
    @SerializedName("next")
    val next: String, // https://rickandmortyapi.com/api/character?page=2
    @SerializedName("prev")
    val prev: Any, // null
)

data class CharacterDto(
    @SerializedName("id")
    val id: Int, // 1
    @SerializedName("name")
    val name: String, // Rick Sanchez
    @SerializedName("status")
    val status: String, // Alive
    @SerializedName("species")
    val species: String, // Human
    @SerializedName("type")
    val type: String,
    @SerializedName("gender")
    val gender: String, // Male
    @SerializedName("image")
    val image: String, // https://rickandmortyapi.com/api/character/avatar/1.jpeg
    @SerializedName("episode")
    val episode: List<String?>,
    @SerializedName("url")
    val url: String, // https://rickandmortyapi.com/api/character/1
    @SerializedName("created")
    val created: String, // 2017-11-04T18:48:46.250Z
)

fun CharacterDto.toDomain() = Character(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    image = image,
    episode = episode,
    url = url,
    created = created
)