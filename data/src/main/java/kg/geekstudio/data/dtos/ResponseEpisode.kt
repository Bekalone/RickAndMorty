package kg.geekstudio.data.dtos


import com.google.gson.annotations.SerializedName
import kg.geekstudio.domain.model.Episode

data class EpisodeDto(
    @SerializedName("id")
    val id: Int, // 1
    @SerializedName("name")
    val name: String, // Pilot
    @SerializedName("air_date")
    val airDate: String, // December 2, 2013
    @SerializedName("episode")
    val episode: String, // S01E01
    @SerializedName("characters")
    val characters: List<String?>,
    @SerializedName("url")
    val url: String, // https://rickandmortyapi.com/api/episode/1
    @SerializedName("created")
    val created: String, // 2017-11-10T12:56:33.798Z
)

fun EpisodeDto.toDomain() = Episode(
    id = id,
    name = name,
    airDate = airDate,
    episode = episode,
    characters = characters,
    url = url,
    created = created
)