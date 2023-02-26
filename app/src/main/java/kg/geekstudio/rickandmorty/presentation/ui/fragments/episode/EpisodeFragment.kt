package kg.geekstudio.rickandmorty.presentation.ui.fragments.episode

import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import kg.geekstudio.rickandmorty.R
import kg.geekstudio.rickandmorty.databinding.FragmentEpisodeBinding
import kg.geekstudio.rickandmorty.presentation.adapters.EpisodePagingAdapter
import kg.geekstudio.rickandmorty.presentation.adapters.paging.LoadStateAdapter
import kg.geekstudio.rickandmorty.core.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class EpisodeFragment :
    BaseFragment<EpisodeViewModel, FragmentEpisodeBinding>(R.layout.fragment_episode) {

    override val viewModel: EpisodeViewModel by viewModel()
    override val binding by viewBinding(FragmentEpisodeBinding::bind)
    private val adapter = EpisodePagingAdapter()

    override fun initialize() {
        setupEpisodeRecycler()
    }

    private fun setupEpisodeRecycler() = with(binding) {
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = adapter.withLoadStateFooter(
            footer = LoadStateAdapter { adapter.retry() })

        adapter.addLoadStateListener { loadStates ->
            recyclerview.isVisible = loadStates.refresh is LoadState.NotLoading
            binding.loader.isVisible = loadStates.refresh is LoadState.Loading
        }
    }

    override fun setupRequests() {
        fetchEpisode()
        setupSearch()
    }

    private fun fetchEpisode() {
        viewModel.fetchEpisode().collectPaging { adapter.submitData(it) }
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
        viewModel.fetchEpisode(name = name).collectPaging {
            adapter.submitData(it)
        }
    }
}
