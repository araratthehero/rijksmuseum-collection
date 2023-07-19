package com.mnatsakanyan.domain.artobjectcollection

import androidx.paging.PagingData
import androidx.paging.map
import com.mnatsakanyan.data.repository.artobjectCollection.ArtObjectCollectionRepository
import com.mnatsakanyan.domain.model.ArtObject
import com.mnatsakanyan.domain.model.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class GetArtObjectCollectionListUseCaseImpl @Inject constructor(
        private val artObjectCollectionRepository: ArtObjectCollectionRepository
) : GetArtObjectCollectionListUseCase {

    override operator fun invoke(): Flow<PagingData<ArtObject>> =
            artObjectCollectionRepository.fetchArtObjectCollectionList()
                    .map { requestedArtObjects ->
                        requestedArtObjects.map { requestedArtObject ->
                            requestedArtObject.asExternalModel()
                        }
                    }
}
