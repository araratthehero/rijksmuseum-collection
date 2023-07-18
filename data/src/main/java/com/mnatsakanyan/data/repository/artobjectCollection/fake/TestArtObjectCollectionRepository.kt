package com.mnatsakanyan.data.repository.artobjectCollection.fake

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.paging.testing.asPagingSourceFactory
import com.mnatsakanyan.data.model.RequestedArtObject
import com.mnatsakanyan.data.model.asExternalModel
import com.mnatsakanyan.data.network.fake.TestNetworkDataSource
import com.mnatsakanyan.data.repository.artobjectCollection.ArtObjectCollectionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TestArtObjectCollectionRepository(
        sizeOfList: Int = LIST_SIZE
) : ArtObjectCollectionRepository {

    private val testNetworkDataSource: TestNetworkDataSource =
            TestNetworkDataSource(sizeOfList)

    private val listOfItems = testNetworkDataSource.listOfItems

    override fun fetchArtObjectCollectionList(): Flow<PagingData<RequestedArtObject>> = Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = listOfItems.asPagingSourceFactory()
    ).flow.map { pagingData ->
        pagingData.map { artObject -> artObject.asExternalModel() }
    }

    suspend fun getRequestedListOfItems(page: Int) =
            testNetworkDataSource.getCollectionList(page, PAGE_SIZE).map { item ->
                item.asExternalModel()
            }

    companion object {
        private const val LIST_SIZE = 50
        private const val PAGE_SIZE = 5
    }
}
