package com.mnatsakanyan.data.repository.fake

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.paging.testing.asPagingSourceFactory
import com.mnatsakanyan.data.model.RequestedArtObject
import com.mnatsakanyan.data.model.asExternalModel
import com.mnatsakanyan.data.network.fake.TestNetworkDataSource
import com.mnatsakanyan.data.repository.ArtObjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class TestArtObjectRepository(
        sizeOfList: Int = LIST_SIZE
) : ArtObjectRepository {

    private val testNetworkDataSource: TestNetworkDataSource = TestNetworkDataSource(sizeOfList)

    private val listOfItems = testNetworkDataSource.listOfItems

    override fun fetchArtObjectCollection(): Flow<PagingData<RequestedArtObject>> = Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = listOfItems.asPagingSourceFactory()
    ).flow.map { pagingData ->
        pagingData.map { artObject -> artObject.asExternalModel() }
    }

    override fun fetchArtObject(artObjectNumber: String): Flow<RequestedArtObject> = flow {
        emit(testNetworkDataSource.getArtObjectDetail(artObjectNumber = artObjectNumber)
                     .asExternalModel())
    }

    suspend fun getRequestedCollection(page: Int) =
            testNetworkDataSource.getCollection(page, PAGE_SIZE).map { item ->
                item.asExternalModel()
            }

    companion object {
        private const val LIST_SIZE = 50
        private const val PAGE_SIZE = 5
    }
}
