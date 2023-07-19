package com.mnatsakanyan.domain.artobjectcollection.fake

import androidx.paging.PagingData
import androidx.paging.map
import com.mnatsakanyan.data.repository.artobjectCollection.fake.TestArtObjectCollectionRepository
import com.mnatsakanyan.domain.artobjectcollection.GetArtObjectCollectionListUseCase
import com.mnatsakanyan.domain.artobjectcollection.GetArtObjectCollectionListUseCaseImpl
import com.mnatsakanyan.domain.model.ArtObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class TestGetArtObjectCollectionListUseCase(
        sizeOfList: Int = LIST_SIZE,
        private val breakBetweenAuthors: Int = BREAK_BETWEEN_AUTHORS
) : GetArtObjectCollectionListUseCase {
    private val testArtObjectCollectionRepository =
            TestArtObjectCollectionRepository(sizeOfList)

    override operator fun invoke(): Flow<PagingData<ArtObject>> =
            GetArtObjectCollectionListUseCaseImpl(testArtObjectCollectionRepository).invoke()
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
