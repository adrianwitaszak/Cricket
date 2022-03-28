package com.adwi.cricket.feature.auth.ui

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adwi.cricket.core.LoadingState
import com.adwi.cricket.feature.auth.R
import com.adwi.cricket.feature.auth.ui.components.AuthHeader
import com.adwi.cricket.feature.auth.ui.components.GoogleSigningButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import timber.log.Timber

const val TEST_TAG_AUTH_SIGN_IN_BUTTON = "sign_in_button"
const val TEST_TAG_AUTH_HEADER = "header"
const val TEST_TAG_AUTH_BY_CONTINUING = "by_continuing"
const val TEST_TAG_AUTH_PROGRESS = "progress"

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = get(),
    appName: String,
    goHome: () -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val loadingState by viewModel.loadingState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
                viewModel.signInWithGoogle(credential)
            } catch (e: ApiException) {
                Timber.tag("TAG").w(e, "Google sign in failed")
            }
        }

    val context = LocalContext.current
    val token = stringResource(R.string.default_web_client_id)
    val gso =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(token)
            .requestEmail()
            .build()

    val googleSignInClient = GoogleSignIn.getClient(context, gso)

    when (loadingState) {
        is LoadingState.FAILED -> {
            val message = stringResource(id = R.string.login_failed)
            LaunchedEffect(true) {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(message)
                }
            }
            Timber.d("AuthScreen - failed")
        }
        is LoadingState.SUCCESS -> {
            goHome()
            Timber.d("AuthScreen - goHome")
        }
        else -> {}
    }

    Scaffold(
        scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState),
        topBar = {
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    // TODO(add feedback menu here)
                }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
            if (loadingState is LoadingState.Loading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
        },
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            AnimatedVisibility(visible = loadingState is LoadingState.Loading) {
                CircularProgressIndicator(modifier = Modifier.testTag(TEST_TAG_AUTH_PROGRESS))
            }
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
                    ) { launcher.launch(googleSignInClient.signInIntent) }
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

