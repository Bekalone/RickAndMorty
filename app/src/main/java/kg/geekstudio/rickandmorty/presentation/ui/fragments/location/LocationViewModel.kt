package kg.geekstudio.rickandmorty.presentation.ui.fragments.location

import kg.geekstudio.domain.usecase.FetchLocationUseCase
import kg.geekstudio.rickandmorty.presentation.base.BaseViewModel
import kg.geekstudio.rickandmorty.presentation.model.toUI

class LocationViewModel(private val fetchLocationUseCase: FetchLocationUseCase): BaseViewModel() {

    fun fetchLocation(name:String? = null) = fetchLocationUseCase(name).collectPagingRequest { it.toUI() }
}