package com.mnatsakanyan.domain.artobjectcollection

import androidx.paging.testing.asSnapshot
import com.mnatsakanyan.data.repository.artobjectCollection.fake.TestArtObjectCollectionRepository
import com.mnatsakanyan.domain.model.asExternalModel
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertTrue

class GetArtObjectCollectionListUseCaseImplTest {

    private val collectionRepository = TestArtObjectCollectionRepository()

    private val useCase = GetArtObjectCollectionListUseCaseImpl(collectionRepository)

    @Test
    fun invokeReturnMappedPagingDataArtObjects() = runTest {
        val expectedResult = collectionRepository.getRequestedListOfItems(1).map { item ->
            item.asExternalModel()
        }

        val result = useCase.invoke().asSnapshot()
        assertTrue { result.containsAll(expectedResult) }
    }
}
