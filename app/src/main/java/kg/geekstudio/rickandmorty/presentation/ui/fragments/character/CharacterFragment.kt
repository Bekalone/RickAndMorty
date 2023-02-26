package kg.geekstudio.rickandmorty.presentation.ui.fragments.character

import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import kg.geekstudio.rickandmorty.R
import kg.geekstudio.rickandmorty.databinding.FragmentCharacterBinding
import kg.geekstudio.rickandmorty.presentation.adapters.CharacterPagingAdapter
import kg.geekstudio.rickandmorty.core.base.BaseFragment
import kg.geekstudio.rickandmorty.presentation.model.CharacterFilter
import org.koin.androidx.viewmodel.ext.android.viewModel
import kg.geekstudio.rickandmorty.presentation.adapters.paging.LoadStateAdapter
import kg.geekstudio.rickandmorty.core.extensions.resultListener

class CharacterFragment :
    BaseFragment<CharacterViewModel, FragmentCharacterBinding>(R.layout.fragment_character) {

    override val viewModel: CharacterViewModel by viewModel()
    override val binding by viewBinding(FragmentCharacterBinding::bind)
    private val adapter = CharacterPagingAdapter()
    private var characterFilter = CharacterFilter()
    private var characterName = String()

    override fun initialize() {
        setupCharacterRecycler()
    }

    private fun setupCharacterRecycler() = with(binding) {
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = adapter.withLoadStateFooter(
            footer = LoadStateAdapter { adapter.retry() }
        )

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
        viewModel.fetchCharacter().collectPaging { adapter.submitData(it) }
    }

    private fun setupSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    characterName = newText
                    fetchCharacterSearchName(characterName)

                } else fetchCharacterSearchName("")
                return false
            }
        })
    }

    private fun fetchCharacterSearchName(name: String) {
        viewModel.fetchCharacter(name = name,
            characterFilter.status,
            characterFilter.species,
            characterFilter.gender).collectPaging {
            adapter.submitData(it)
        }
    }

    override fun setupListeners() {
        super.setupListeners()
        binding.btnFilterDialog.setOnClickListener {
            findNavController().navigate(R.id.filterFragment)
        }
        resultListener()
    }

    private fun resultListener() {
        val filter = resultListener<CharacterFilter>("filter")
        characterFilter.status = filter?.status
        characterFilter.species = filter?.species
        characterFilter.gender = filter?.gender

        viewModel.fetchCharacter(
            name = characterName,
            status = characterFilter.status,
            species = characterFilter.species,
            gender = characterFilter.gender)
            .collectPaging { adapter.submitData(it) }
    }
}
