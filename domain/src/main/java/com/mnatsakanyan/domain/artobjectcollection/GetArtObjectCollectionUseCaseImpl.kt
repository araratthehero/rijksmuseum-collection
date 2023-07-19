package com.mnatsakanyan.domain.artobjectcollection

import androidx.paging.PagingData
import androidx.paging.map
import com.mnatsakanyan.data.repository.ArtObjectRepository
import com.mnatsakanyan.domain.model.ArtObject
import com.mnatsakanyan.domain.model.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class GetArtObjectCollectionUseCaseImpl @Inject constructor(
        private val artObjectRepository: ArtObjectRepository
) : GetArtObjectCollectionUseCase {

    override operator fun invoke(): Flow<PagingData<ArtObject>> =
            artObjectRepository.fetchArtObjectCollection()
                    .map { requestedArtObjects ->
                        requestedArtObjects.map { requestedArtObject ->
                            requestedArtObject.asExternalModel()
                        }
                    }
}
