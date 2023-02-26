package kg.geekstudio.rickandmorty.di

import kg.geekstudio.rickandmorty.presentation.ui.fragments.character.CharacterViewModel
import kg.geekstudio.rickandmorty.presentation.ui.fragments.episode.EpisodeViewModel
import kg.geekstudio.rickandmorty.presentation.ui.fragments.location.LocationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<CharacterViewModel> {
        CharacterViewModel(fetchCharacterUseCase = get())
    }

    viewModel<LocationViewModel> {
        LocationViewModel(fetchLocationUseCase = get())
    }

    viewModel<EpisodeViewModel> {
        EpisodeViewModel(fetchEpisodeUseCase = get())
    }


}