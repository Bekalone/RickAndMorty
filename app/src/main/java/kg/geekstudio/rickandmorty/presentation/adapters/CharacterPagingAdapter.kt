package kg.geekstudio.rickandmorty.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import kg.geekstudio.rickandmorty.R
import kg.geekstudio.rickandmorty.databinding.ItemCharacterBinding
import kg.geekstudio.rickandmorty.core.base.BaseDiffUtil
import kg.geekstudio.rickandmorty.presentation.model.CharacterUI

class CharacterPagingAdapter :
    PagingDataAdapter<CharacterUI, CharacterPagingAdapter.CharacterViewHolder>(BaseDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(ItemCharacterBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        ViewHolder(binding.root) {
        fun bind(item: CharacterUI?) = with(binding) {
            item?.apply {
                progressImage.isVisible = true
                imageView.load(image){ listener { _, _ -> progressImage.isGone = true }}
                tvName.text = name
                tvStatus.text = status
                tvSpecies.text = species
                tvGender.text = gender
                when (status) {
                    "Alive" -> imageStatus.setImageResource(R.color.green)
                    "Dead" -> imageStatus.setImageResource(R.color.red)
                    "unknown" -> imageStatus.setImageResource(R.color.grey)
                }
                when (gender) {
                    "Male" -> imageGender.setImageResource(R.drawable.ic_male_gender)
                    "Female" -> imageGender.setImageResource(R.drawable.ic_female_gender)
                    "unknown" -> imageGender.setImageResource(R.color.grey)
                }
            }
        }
    }
}