package kg.geekstudio.rickandmorty.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import kg.geekstudio.rickandmorty.databinding.ItemCharacterBinding
import kg.geekstudio.rickandmorty.databinding.ItemLocationBinding
import kg.geekstudio.rickandmorty.presentation.base.BaseDiffUtil
import kg.geekstudio.rickandmorty.presentation.model.CharacterUI
import kg.geekstudio.rickandmorty.presentation.model.LocationUI

class LocationPagingAdapter :
    PagingDataAdapter<LocationUI, LocationPagingAdapter.LocationViewHolder>(BaseDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(ItemLocationBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class LocationViewHolder(private val binding: ItemLocationBinding) :
        ViewHolder(binding.root) {
        fun bind(item: LocationUI) = with(binding){
            item.apply {
                tvName.text = name
                tvType.text = type
                tvDimension.text = dimension
            }
        }
    }
}