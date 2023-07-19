package com.mnatsakanyan.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.map
import com.mnatsakanyan.data.model.NetworkArtObject
import com.mnatsakanyan.data.model.RequestedArtObject
import com.mnatsakanyan.data.model.asExternalModel
import com.mnatsakanyan.data.network.NetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

internal class DefaultArtObjectRepository @Inject constructor(
        private val networkDataSource: NetworkDataSource,
        private val pageSize: Int,
        private val dispatcher: CoroutineDispatcher
) : PagingSource<Int, NetworkArtObject>(), ArtObjectRepository {

    override fun getRefreshKey(state: PagingState<Int, NetworkArtObject>) =
            state.anchorPosition?.let { anchorPosition ->
                state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                    ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
            }

    override suspend fun load(params: LoadParams<Int>) = withContext(dispatcher) {
        try {
            val page = params.key ?: 1
            val response = networkDataSource.getCollection(pageNumber = page,
                                                           itemCountPerPage = params.loadSize)

            LoadResult.Page(
                    data = response,
                    prevKey = if (page == 1) null else page.minus(1),
                    nextKey = if (response.isEmpty()) null else page.plus(1),
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun fetchArtObjectCollection() = Pager(
            config = PagingConfig(pageSize),
            pagingSourceFactory = {
                this
            }
    ).flow.map { pagingData ->
        pagingData.map { networkArtObject -> networkArtObject.asExternalModel() }
    }

    override fun fetchArtObject(artObjectNumber: String): Flow<RequestedArtObject> = flow {
        val artObject = networkDataSource.getArtObjectDetail(artObjectNumber).asExternalModel()
        emit(artObject)
    }.flowOn(dispatcher)
}

