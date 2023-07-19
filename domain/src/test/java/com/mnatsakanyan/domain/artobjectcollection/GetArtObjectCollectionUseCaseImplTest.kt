package com.mnatsakanyan.domain.artobjectcollection

import androidx.paging.testing.asSnapshot
import com.mnatsakanyan.data.repository.fake.TestArtObjectRepository
import com.mnatsakanyan.domain.model.asExternalModel
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertTrue

class GetArtObjectCollectionUseCaseImplTest {

    private val collectionRepository = TestArtObjectRepository()

    private val useCase = GetArtObjectCollectionUseCaseImpl(collectionRepository)

    @Test
    fun invokeReturnMappedPagingDataArtObjects() = runTest {
        val expectedResult = collectionRepository.getRequestedCollection(1).map { item ->
            item.asExternalModel()
        }

        val result = useCase.invoke().asSnapshot()
        assertTrue { result.containsAll(expectedResult) }
    }
}
