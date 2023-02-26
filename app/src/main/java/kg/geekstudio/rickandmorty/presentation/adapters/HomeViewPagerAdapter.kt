package kg.geekstudio.rickandmorty.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kg.geekstudio.rickandmorty.presentation.ui.fragments.character.CharacterFragment
import kg.geekstudio.rickandmorty.presentation.ui.fragments.episode.EpisodeFragment
import kg.geekstudio.rickandmorty.presentation.ui.fragments.location.LocationFragment

class HomeViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CharacterFragment()
            1 -> LocationFragment()
            else -> EpisodeFragment()
        }
    }
}