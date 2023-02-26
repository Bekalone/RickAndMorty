package kg.geekstudio.rickandmorty.presentation.model

import kg.geekstudio.domain.model.Episode
import kg.geekstudio.rickandmorty.presentation.base.BaseDiffModel

data class EpisodeUI(
    override val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<String?>,
    val url: String,
    val created: String
): BaseDiffModel<Int>

fun Episode.toUI() = EpisodeUI(
    id, name, airDate, episode, characters, url, created
)
