package com.adwi.cricket.feature.auth.ui

import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adwi.cricket.components.CricketProgressIndicator
import com.adwi.cricket.core.LoadingState
import com.adwi.cricket.feature.auth.R
import com.adwi.cricket.feature.auth.ui.components.AuthHeader
import com.adwi.cricket.feature.auth.ui.components.GoogleSigningButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch

const val TEST_TAG_AUTH_SIGN_IN_BUTTON = "sign_in_button"
const val TEST_TAG_AUTH_HEADER = "header"
const val TEST_TAG_AUTH_BY_CONTINUING = "by_continuing"
const val TEST_TAG_AUTH_PROGRESS = "progress"

@Composable
fun AuthScreen(
    state: AuthScreenState,
    intent: (AuthScreenIntent) -> Unit,
    appName: String,
    goHome: () -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    val launcher = getSignInLauncherActivity(intent)
    val googleSignInClient = getGoogleSignInClient(
        context = context,
        token = stringResource(R.string.default_web_client_id)
    )

    handleAuthLoadingState(
        state = state.loadingState,
        snackbarHostState = snackbarHostState,
        success = goHome,
    )

    Scaffold(
        scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CricketProgressIndicator(
                isLoading = state.loadingState is LoadingState.LOADING,
                modifier = Modifier.testTag(TEST_TAG_AUTH_PROGRESS)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Spacer(modifier = Modifier.height(50.dp))
                    AuthHeader(
                        modifier = Modifier.testTag(TEST_TAG_AUTH_HEADER),
                        appName = appName)
                    Spacer(modifier = Modifier.weight(1f))
                    GoogleSigningButton(
                        modifier = Modifier.testTag(TEST_TAG_AUTH_SIGN_IN_BUTTON)
                    ) {
                        launcher.launch(googleSignInClient.signInIntent)
                    }
                    Spacer(modifier = Modifier.size(12.dp))
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag(TEST_TAG_AUTH_BY_CONTINUING),
                        textAlign = TextAlign.Center,
                        fontSize = 6.sp,
                        fontWeight = FontWeight.Light,
                        text = stringResource(id = R.string.by_continuing)
                    )
                }
            )
        }
    }
}

private fun getGoogleSignInClient(context: Context, token: String): GoogleSignInClient {
    val gso =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(token)
            .requestEmail()
            .build()

    return GoogleSignIn.getClient(context, gso)
}

private fun getSignInLauncherActivity(
    intent: (AuthScreenIntent) -> Unit,
) =
    rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
            intent(AuthScreenIntent.SignIntWithCredentials(credential))
        } catch (e: ApiException) {
            println("Google sign in failed")
        }
    }

private fun handleAuthLoadingState(
    state: LoadingState,
    snackbarHostState: SnackbarHostState,
    success: @Composable () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    when (state) {
        is LoadingState.FAILED -> {
            val message = stringResource(id = R.string.login_failed)
            LaunchedEffect(true) {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(message)
                }
            }
        }
        is LoadingState.SUCCESS -> {
            success()
        }
        else -> {}
    }
}
