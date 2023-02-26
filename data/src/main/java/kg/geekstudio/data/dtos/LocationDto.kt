package kg.geekstudio.data.dtos

import com.google.gson.annotations.SerializedName
import kg.geekstudio.domain.model.Location

data class LocationDto(
    @SerializedName("id") val id: Int, // 1
    @SerializedName("name") val name: String, // Earth (C-137)
    @SerializedName("type") val type: String, // Planet
    @SerializedName("dimension") val dimension: String, // Dimension C-137
    @SerializedName("residents") val residents: List<String?>,
    @SerializedName("url") val url: String, // https://rickandmortyapi.com/api/location/1
    @SerializedName("created") val created: String, // 2017-11-10T12:42:04.162Z
)

fun LocationDto.toDomain() = Location(id = id,
    name = name,
    type = type,
    dimension = dimension,
    residents = residents,
    url = url,
    created = created

)