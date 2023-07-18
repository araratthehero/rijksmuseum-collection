package com.mnatsakanyan.data.repository.artobjectCollection

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mnatsakanyan.data.model.NetworkArtObject
import com.mnatsakanyan.data.model.RequestedArtObject
import com.mnatsakanyan.data.network.NetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultArtObjectCollectionRepository @Inject constructor(
        private val networkDataSource: NetworkDataSource,
        private val pageSize: Int = PAGE_SIZE,
        private val dispatcher: CoroutineDispatcher
) : PagingSource<Int, NetworkArtObject>(), ArtObjectCollectionRepository {

    override fun getRefreshKey(state: PagingState<Int, NetworkArtObject>) =
            state.anchorPosition?.let { anchorPosition ->
                state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                    ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
            }

    override suspend fun load(params: LoadParams<Int>) = withContext(dispatcher) {
        try {
            val page = params.key ?: 1
            val response = networkDataSource.getCollectionList(pageNumber = page,
                                                                     itemCountPerPage = params.loadSize)

            LoadResult.Page(
                    data = response,
                    prevKey = if (page == 1) null else page.minus(1),
                    nextKey = if (response.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun fetchArtObjectCollectionList(): Flow<PagingData<RequestedArtObject>> {
        TODO("Not yet implemented")
    }

    companion object {
        private const val PAGE_SIZE = 15
    }
}
