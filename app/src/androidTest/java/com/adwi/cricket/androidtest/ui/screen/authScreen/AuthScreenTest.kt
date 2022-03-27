package com.adwi.cricket.androidtest.ui.screen.authScreen

import androidx.activity.viewModels
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.adwi.cricket.app.MainActivity
import com.adwi.cricket.core.LoadingState
import com.adwi.cricket.feature.auth.ui.*
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class AuthScreenTest {

    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule = createComposeRule()

    private lateinit var viewModel: AuthViewModel

    @Before
    fun setUp() {
//        viewModel = composeTestRule.activity.viewModels<AuthViewModel>().value
//        viewModel.signOut()
//        viewModel = AuthViewModel()
        composeTestRule.mainClock.autoAdvance = true
    }

    @Test
    fun authScreen_displays_signInButton() {
        composeTestRule.onNodeWithTag(TEST_TAG_AUTH_SIGN_IN_BUTTON)
            .assertIsDisplayed()
    }

    @Test
    fun authScreen_displays_header() {
        composeTestRule.onNodeWithTag(TEST_TAG_AUTH_HEADER)
            .assertIsDisplayed()
    }

    @Test
    fun authScreen_displays_byContinuing() {
        composeTestRule.onNodeWithTag(TEST_TAG_AUTH_BY_CONTINUING)
            .assertIsDisplayed()
    }
}