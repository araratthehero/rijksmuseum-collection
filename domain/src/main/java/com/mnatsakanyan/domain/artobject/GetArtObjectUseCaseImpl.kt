package com.mnatsakanyan.domain.artobject

import com.mnatsakanyan.data.repository.artobject.ArtObjectRepository
import com.mnatsakanyan.domain.model.ArtObject
import com.mnatsakanyan.domain.model.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class GetArtObjectUseCaseImpl @Inject constructor(
        private val artObjectRepository: ArtObjectRepository
) : GetArtObjectUseCase {

    override operator fun invoke(artObjectNumber: String): Flow<ArtObject> =
            artObjectRepository.fetchArtObject(artObjectNumber).map { requestedArtObject ->
                requestedArtObject.asExternalModel()
            }
}
