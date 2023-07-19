package com.mnatsakanyan.data.artobjectcollection

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.testing.TestPager
import androidx.paging.testing.asSnapshot
import com.mnatsakanyan.data.model.asExternalModel
import com.mnatsakanyan.data.network.fake.TestFailedNetworkDataSource
import com.mnatsakanyan.data.network.fake.TestNetworkDataSource
import com.mnatsakanyan.data.repository.artobjectCollection.DefaultArtObjectCollectionRepository
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class DefaultArtObjectCollectionRepositoryTest {

    private val pageSize = 5

    private val pagingConfig = PagingConfig(pageSize)
    private val networkDataSource = TestNetworkDataSource(100)
    private val failedNetworkDataSource = TestFailedNetworkDataSource()

    private val pagingSource = DefaultArtObjectCollectionRepository(networkDataSource,
                                                                    dispatcher = Unconfined)
    private val failedPagingSource =
            DefaultArtObjectCollectionRepository(failedNetworkDataSource,
                                                 dispatcher = Unconfined)

    @Test
    fun pagerRefreshWhenSuccessfulReturnsPage() = runTest {
        val pager = TestPager(pagingConfig, pagingSource)
        val expectedListItems = failedNetworkDataSource.getCollectionList(1, pageSize)

        val result = pager.refresh() as PagingSource.LoadResult.Page

        assertTrue(result.data.containsAll(expectedListItems))
    }

    @Test
    fun pagerAppendWhenOnSuccessfulReturnsNextPage() = runTest {
        val pager = TestPager(pagingConfig, pagingSource)
        val expectedListItems = networkDataSource.getCollectionList(4, pageSize)

        val result = with(pager) {
            refresh()
            append()
            append()
            append()
        } as PagingSource.LoadResult.Page

        assertTrue(result.data.containsAll(expectedListItems))
    }

    @Test
    fun pagerRefreshWhenOnNotSuccessfulReturnsError() = runTest {
        val pager = TestPager(pagingConfig, failedPagingSource)

        val result = pager.refresh()
        assertTrue(result is PagingSource.LoadResult.Error)

        val page = pager.getLastLoadedPage()
        assertNull(page)
    }

    @Test
    fun fetchArtObjectCollectionListContainsFirstPageList() = runTest {
        val expectedList =
                networkDataSource.getCollectionList(1, pageSize).map { networkArtObject ->
                    networkArtObject.asExternalModel()
                }

        val result = pagingSource.fetchArtObjectCollectionList().asSnapshot()

        assertTrue(result.containsAll(expectedList))
    }
}
