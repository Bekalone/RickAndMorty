package kg.geekstudio.domain.repositories

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kg.geekstudio.domain.model.Character

interface CharacterRepository {

    fun fetchCharacter(
        name: String?,
        status: String?,
        species: String?,
        gender: String?,
    ): Flow<PagingData<Character>>
}