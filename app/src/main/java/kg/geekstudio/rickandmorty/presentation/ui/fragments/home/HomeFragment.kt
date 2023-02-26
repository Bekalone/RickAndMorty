package kg.geekstudio.rickandmorty.presentation.ui.fragments.home

import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import kg.geekstudio.rickandmorty.R
import kg.geekstudio.rickandmorty.databinding.FragmentHomeBinding
import kg.geekstudio.rickandmorty.presentation.adapters.HomeViewPagerAdapter
import kg.geekstudio.rickandmorty.core.base.BaseFragmentWithoutViewModel

class HomeFragment : BaseFragmentWithoutViewModel<FragmentHomeBinding>(R.layout.fragment_home) {

    override val binding by viewBinding(FragmentHomeBinding::bind)

    override fun setupListeners() {
        binding.viewpager.adapter = HomeViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, pos ->
            when (pos) {
                0 -> tab.text = "character"
                1 -> tab.text = "location"
                2 -> tab.text = "episode"
            }
        }.attach()
    }

}