package kg.geekstudio.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kg.geekstudio.data.apiservice.CharacterApi
import kg.geekstudio.data.pagingsources.CharacterPagingSource
import kg.geekstudio.domain.model.Character
import kg.geekstudio.domain.repositories.CharacterRepository
import kotlinx.coroutines.flow.Flow

class CharacterRepositoryImpl(private val characterApi: CharacterApi) : CharacterRepository {
    override fun fetchCharacter(
        name: String?,
        status: String?,
        species: String?,
        gender: String?,
    ): Flow<PagingData<Character>> {
        return Pager(config = PagingConfig(pageSize = 20,
            prefetchDistance = 20,
            enablePlaceholders = true,
            initialLoadSize = 20,
            maxSize = Int.MAX_VALUE,
            jumpThreshold = Int.MIN_VALUE),
            pagingSourceFactory = {
            CharacterPagingSource(characterApi, name, status, species, gender)
        }).flow
    }

}