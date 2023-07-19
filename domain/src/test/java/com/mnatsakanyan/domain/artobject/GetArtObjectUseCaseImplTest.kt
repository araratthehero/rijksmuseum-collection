package com.mnatsakanyan.domain.artobject

import com.mnatsakanyan.data.repository.fake.TestArtObjectRepository
import com.mnatsakanyan.domain.model.Result
import com.mnatsakanyan.domain.model.asExternalModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class GetArtObjectUseCaseImplTest {

    private val artObjectRepository = TestArtObjectRepository()

    private val useCase = GetArtObjectUseCaseImpl(artObjectRepository)

    @Test
    fun invokeReturnMappedArtObject() = runTest {
        val artObjectNumber = "123456"
        val expectedResult = artObjectRepository.fetchArtObject(artObjectNumber).first()

        val result = useCase(artObjectNumber).last()

        assertEquals(expectedResult.asExternalModel(), (result as Result.Success).data)
    }
}
