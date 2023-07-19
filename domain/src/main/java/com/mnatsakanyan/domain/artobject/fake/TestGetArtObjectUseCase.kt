package com.mnatsakanyan.domain.artobject.fake

import com.mnatsakanyan.data.repository.artobject.fake.TestArtObjectRepository
import com.mnatsakanyan.domain.artobject.GetArtObjectUseCase
import com.mnatsakanyan.domain.model.ArtObject
import com.mnatsakanyan.domain.model.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class TestGetArtObjectUseCase : GetArtObjectUseCase {

    private val testArtObjectRepository = TestArtObjectRepository()

    override operator fun invoke(artObjectNumber: String): Flow<ArtObject> =
            testArtObjectRepository.fetchArtObject(
                    artObjectNumber = artObjectNumber
            ).map { requestedArtObject -> requestedArtObject.asExternalModel() }
}
