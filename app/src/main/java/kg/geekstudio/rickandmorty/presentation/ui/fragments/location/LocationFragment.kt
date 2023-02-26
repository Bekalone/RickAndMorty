package kg.geekstudio.rickandmorty.presentation.ui.fragments.location

import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import kg.geekstudio.rickandmorty.R
import kg.geekstudio.rickandmorty.databinding.FragmentLocationBinding
import kg.geekstudio.rickandmorty.presentation.adapters.LocationPagingAdapter
import kg.geekstudio.rickandmorty.presentation.adapters.paging.LoadStateAdapter
import kg.geekstudio.rickandmorty.core.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationFragment :
    BaseFragment<LocationViewModel, FragmentLocationBinding>(R.layout.fragment_location) {

    override val viewModel: LocationViewModel by viewModel()
    override val binding by viewBinding(FragmentLocationBinding::bind)
    private val adapter = LocationPagingAdapter()

    override fun initialize() {
        setupLocationRecycler()
    }

    private fun setupLocationRecycler() = with(binding) {
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = adapter.withLoadStateFooter(
            footer = LoadStateAdapter { adapter.retry() })

        adapter.addLoadStateListener { loadStates ->
            recyclerview.isVisible = loadStates.refresh is LoadState.NotLoading
            binding.loader.isVisible = loadStates.refresh is LoadState.Loading
        }
    }

    override fun setupRequests() {
        fetchCharacter()
        setupSearch()
    }

    private fun fetchCharacter() {
        viewModel.fetchLocation().collectPaging { adapter.submitData(it) }
    }

    private fun setupSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    fetchLocationSearchName(newText)

                } else fetchLocationSearchName("")
                return false
            }
        })
    }

    private fun fetchLocationSearchName(name: String) {
        viewModel.fetchLocation(name = name).collectPaging {
            adapter.submitData(it)
        }

    }
}
