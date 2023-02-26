package kg.geekstudio.domain.usecase

import kg.geekstudio.domain.repositories.CharacterRepository
import kg.geekstudio.domain.repositories.LocationRepository

class FetchLocationUseCase(private val repository: LocationRepository) {

    operator fun invoke(
        name: String?
    ) = repository.fetchLocation(name)
}