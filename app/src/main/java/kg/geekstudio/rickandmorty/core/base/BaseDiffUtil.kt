package kg.geekstudio.rickandmorty.core.base

import androidx.recyclerview.widget.DiffUtil

class BaseDiffUtil<T : BaseDiffModel<S>, S> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}