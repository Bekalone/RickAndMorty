package kg.geekstudio.rickandmorty.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import kg.geekstudio.rickandmorty.databinding.ItemCharacterBinding
import kg.geekstudio.rickandmorty.databinding.ItemEpisodeBinding
import kg.geekstudio.rickandmorty.presentation.base.BaseDiffUtil
import kg.geekstudio.rickandmorty.presentation.model.CharacterUI
import kg.geekstudio.rickandmorty.presentation.model.EpisodeUI

class EpisodePagingAdapter :
    PagingDataAdapter<EpisodeUI, EpisodePagingAdapter.EpisodeViewHolder>(BaseDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class EpisodeViewHolder(private val binding: ItemEpisodeBinding) :
        ViewHolder(binding.root) {
        fun bind(item: EpisodeUI) {
            binding.tvName.text = item.name
        }
    }
}