package kg.geekstudio.domain.usecase


import kg.geekstudio.domain.repositories.EpisodeRepository

class FetchEpisodeUseCase(private val repository: EpisodeRepository) {

    operator fun invoke(
        name: String?
    ) = repository.fetchEpisode(name)
}