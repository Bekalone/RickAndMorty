package kg.geekstudio.rickandmorty.presentation.model

data class CharacterFilter(
    var status: String? = null,
    var species: String? = null,
    var gender: String? = null,
) : java.io.Serializable
