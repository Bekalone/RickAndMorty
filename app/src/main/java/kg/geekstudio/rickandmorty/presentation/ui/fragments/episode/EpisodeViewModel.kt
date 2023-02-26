package kg.geekstudio.rickandmorty.presentation.ui.fragments.episode

import kg.geekstudio.domain.usecase.FetchEpisodeUseCase
import kg.geekstudio.rickandmorty.presentation.base.BaseViewModel
import kg.geekstudio.rickandmorty.presentation.model.toUI

class EpisodeViewModel(private val fetchEpisodeUseCase: FetchEpisodeUseCase):BaseViewModel() {

    fun fetchEpisode(name:String? = null) = fetchEpisodeUseCase(name).collectPagingRequest { it.toUI() }
}