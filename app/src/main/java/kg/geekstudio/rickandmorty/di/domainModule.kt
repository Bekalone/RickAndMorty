package kg.geekstudio.rickandmorty.di

import kg.geekstudio.domain.usecase.FetchCharacterUseCase
import kg.geekstudio.domain.usecase.FetchEpisodeUseCase
import kg.geekstudio.domain.usecase.FetchLocationUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<FetchCharacterUseCase> {
        FetchCharacterUseCase(repository = get())
    }
    factory<FetchLocationUseCase> {
        FetchLocationUseCase(repository = get())
    }
    factory<FetchEpisodeUseCase> {
        FetchEpisodeUseCase(repository = get())
    }
}