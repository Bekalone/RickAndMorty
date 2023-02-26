package kg.geekstudio.domain.usecase

import kg.geekstudio.domain.repositories.CharacterRepository

class FetchCharacterUseCase(private val repository: CharacterRepository) {

    operator fun invoke(
        name: String?,
        status: String?,
        species: String?,
        gender: String?,
    ) = repository.fetchCharacter(name, status, species, gender)
}