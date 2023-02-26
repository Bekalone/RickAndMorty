package kg.geekstudio.rickandmorty.presentation.base

interface BaseDiffModel<T> {
    val id: T?
    override fun equals(other: Any?): Boolean
}