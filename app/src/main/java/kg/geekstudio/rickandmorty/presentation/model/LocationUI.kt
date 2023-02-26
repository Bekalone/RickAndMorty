package kg.geekstudio.rickandmorty.presentation.model

import kg.geekstudio.domain.model.Location
import kg.geekstudio.rickandmorty.core.base.BaseDiffModel

data class LocationUI(
    override val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String?>,
    val url: String,
    val created: String,
): BaseDiffModel<Int>

fun Location.toUI() = LocationUI(
    id, name, type, dimension, residents, url, created
)