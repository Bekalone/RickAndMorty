package kg.geekstudio.rickandmorty.presentation.ui.fragments.character

import kg.geekstudio.domain.usecase.FetchCharacterUseCase
import kg.geekstudio.rickandmorty.core.base.BaseViewModel
import kg.geekstudio.rickandmorty.presentation.model.toUI

class CharacterViewModel(private val fetchCharacterUseCase: FetchCharacterUseCase): BaseViewModel() {

    fun fetchCharacter(
        name: String? = null,
        status: String? = null,
        species: String? = null,
        gender: String? = null,
    ) =
        fetchCharacterUseCase(name, status, species, gender).collectPagingRequest { it.toUI() }
}