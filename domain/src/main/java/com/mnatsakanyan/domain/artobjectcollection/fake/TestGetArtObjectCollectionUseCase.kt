package com.mnatsakanyan.domain.artobjectcollection.fake

import androidx.paging.PagingData
import androidx.paging.map
import com.mnatsakanyan.data.repository.fake.TestArtObjectRepository
import com.mnatsakanyan.domain.artobjectcollection.GetArtObjectCollectionUseCase
import com.mnatsakanyan.domain.artobjectcollection.GetArtObjectCollectionUseCaseImpl
import com.mnatsakanyan.domain.model.ArtObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TestGetArtObjectCollectionUseCase(
        sizeOfList: Int = LIST_SIZE,
        private val breakBetweenAuthors: Int = BREAK_BETWEEN_AUTHORS
) : GetArtObjectCollectionUseCase {
    private val testArtObjectCollectionRepository =
            TestArtObjectRepository(sizeOfList)

    override operator fun invoke(): Flow<PagingData<ArtObject>> =
            GetArtObjectCollectionUseCaseImpl(testArtObjectCollectionRepository).invoke()
                    .map { pagingData ->
                        var authorNumber = 0
                        var mappedAuthorCount = 0

                        pagingData.map { artObject ->
                            mappedAuthorCount++
                            if (mappedAuthorCount >= breakBetweenAuthors) {
                                mappedAuthorCount = 1
                                authorNumber++
                            }

                            artObject.copy(author = authorNumber.toString())
                        }
                    }

    companion object {
        private const val LIST_SIZE = 50
        private const val BREAK_BETWEEN_AUTHORS = 5
    }
}
