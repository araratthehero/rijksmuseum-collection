package com.mnatsakanyan.domain.artobject.fake

import com.mnatsakanyan.data.repository.fake.TestArtObjectRepository
import com.mnatsakanyan.domain.artobject.GetArtObjectUseCase
import com.mnatsakanyan.domain.model.ArtObject
import com.mnatsakanyan.domain.model.Result
import com.mnatsakanyan.domain.model.asExternalModel
import com.mnatsakanyan.domain.model.asResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TestGetArtObjectUseCase : GetArtObjectUseCase {

    private val testArtObjectRepository = TestArtObjectRepository()

    override operator fun invoke(artObjectNumber: String): Flow<Result<ArtObject>> =
            testArtObjectRepository.fetchArtObject(
                    artObjectNumber = artObjectNumber
            ).map { requestedArtObject -> requestedArtObject.asExternalModel() }.asResult()
}
