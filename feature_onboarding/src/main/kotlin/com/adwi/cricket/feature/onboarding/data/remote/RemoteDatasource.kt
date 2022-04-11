package com.adwi.cricket.feature.onboarding.data.remote

import com.adwi.cricket.feature.onboarding.domain.CommonSection
import com.adwi.cricket.feature.onboarding.domain.ExpSection

internal interface RemoteDatasource {
    suspend fun getExp(): List<ExpSection>
    fun getCommon(): List<CommonSection>
}