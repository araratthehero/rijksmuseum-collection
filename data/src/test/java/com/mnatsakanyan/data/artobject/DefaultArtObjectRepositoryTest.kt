package com.mnatsakanyan.data.artobject

import com.mnatsakanyan.data.network.fake.TestFailedNetworkDataSource
import com.mnatsakanyan.data.network.fake.TestNetworkDataSource
import com.mnatsakanyan.data.repository.artobject.DefaultArtObjectRepository
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class DefaultArtObjectRepositoryTest {

    private val repository =
            DefaultArtObjectRepository(TestNetworkDataSource(), dispatcher = Unconfined)
    private val failedRepository =
            DefaultArtObjectRepository(TestFailedNetworkDataSource(), dispatcher = Unconfined)

    @Test
    fun fetchArtObjectEmitsArtObjectDetail() = runTest {
        val artObjectNumber = "123456"

        val item = repository.fetchArtObject(artObjectNumber).first()

        assertEquals(artObjectNumber, item.objectNumber)
    }

    @Test(expected = Exception::class)
    fun fetchArtObjectThrowsExceptionWhenRequestFails() = runTest {
        failedRepository.fetchArtObject("123456").first()
    }
}
