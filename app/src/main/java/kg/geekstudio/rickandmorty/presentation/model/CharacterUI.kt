package kg.geekstudio.rickandmorty.presentation.model

import kg.geekstudio.domain.model.Character
import kg.geekstudio.rickandmorty.presentation.base.BaseDiffModel

data class CharacterUI(
    override val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val episode: List<String?>,
    val url: String,
    val created: String
): BaseDiffModel<Int>

fun Character.toUI() = CharacterUI(
    id, name, status, species, type, gender, image, episode, url, created
)
