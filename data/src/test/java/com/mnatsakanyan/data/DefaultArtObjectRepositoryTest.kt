package com.mnatsakanyan.data

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.testing.TestPager
import androidx.paging.testing.asSnapshot
import com.mnatsakanyan.data.model.asExternalModel
import com.mnatsakanyan.data.network.fake.TestFailedNetworkDataSource
import com.mnatsakanyan.data.network.fake.TestNetworkDataSource
import com.mnatsakanyan.data.repository.DefaultArtObjectRepository
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class DefaultArtObjectRepositoryTest {

    private val pageSize = 5

    private val pagingConfig = PagingConfig(pageSize)
    private val networkDataSource = TestNetworkDataSource(100)
    private val failedNetworkDataSource = TestFailedNetworkDataSource()

    private val repository = DefaultArtObjectRepository(networkDataSource = networkDataSource,
                                                        pageSize = pageSize,
                                                        dispatcher = Unconfined)
    private val failedRepository =
            DefaultArtObjectRepository(networkDataSource = failedNetworkDataSource,
                                       pageSize = pageSize,
                                       dispatcher = Unconfined)

    @Test
    fun pagerRefreshWhenSuccessfulReturnsPage() = runTest {
        val pager = TestPager(pagingConfig, repository)
        val expectedItems = networkDataSource.getCollection(1, pageSize)

        val result = pager.refresh() as PagingSource.LoadResult.Page

        assertTrue(result.data.containsAll(expectedItems))
    }

    @Test
    fun pagerAppendWhenOnSuccessfulReturnsNextPage() = runTest {
        val pager = TestPager(pagingConfig, repository)
        val expectedItems = networkDataSource.getCollection(4, pageSize)

        val result = with(pager) {
            refresh()
            append()
            append()
            append()
        } as PagingSource.LoadResult.Page

        assertTrue(result.data.containsAll(expectedItems))
    }

    @Test
    fun pagerRefreshWhenOnNotSuccessfulReturnsError() = runTest {
        val pager = TestPager(pagingConfig, failedRepository)

        val result = pager.refresh()
        assertTrue(result is PagingSource.LoadResult.Error)

        val page = pager.getLastLoadedPage()
        assertNull(page)
    }

    @Test
    fun fetchArtObjectCollectionContainsFirstPageItems() = runTest {
        val expectedItems =
                networkDataSource.getCollection(1, pageSize).map { networkArtObject ->
                    networkArtObject.asExternalModel()
                }

        val result = repository.fetchArtObjectCollection().asSnapshot()

        assertTrue(result.containsAll(expectedItems))
    }
}
