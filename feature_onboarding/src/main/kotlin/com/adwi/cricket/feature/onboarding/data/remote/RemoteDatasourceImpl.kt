package com.adwi.cricket.feature.onboarding.data.remote

import com.adwi.cricket.core.logger.Logger
import com.adwi.cricket.feature.onboarding.domain.CommonSection
import com.adwi.cricket.feature.onboarding.domain.ExpSection
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

internal const val COL_ONBOARDING = "onboarding"
internal const val DOC_EXP = "exp"
internal const val COL_OPTIONS = "options"

internal class RemoteDatasourceImpl(
    dbFirestore: FirebaseFirestore,
//    private val mapper: SectionMapper,
    private val logger: Logger,
) : RemoteDatasource {

    private val onBoardingCollection = dbFirestore.collection(COL_ONBOARDING)

    override suspend fun getExp(): List<ExpSection> {
        val expDoc = onBoardingCollection.document(DOC_EXP)
        val expSnapshot = expDoc.get().await()
        val optionsSnapshot = expDoc.collection(COL_OPTIONS).get().await()

    }

    override fun getCommon(): List<CommonSection> {
        TODO("Not yet implemented")
    }
}