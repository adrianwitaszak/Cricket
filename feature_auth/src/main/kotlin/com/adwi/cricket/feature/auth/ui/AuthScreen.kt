package com.adwi.cricket.feature.auth.ui

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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

@Composable
fun AuthScreen(
    viewModel: AuthViewModel,
    appName: String,
    goHome: () -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val state by viewModel.state.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    when (val loadingState = state.loadingState) {
        is LoadingState.FAILED -> {
            LaunchedEffect(loadingState.msg != null) {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(loadingState.msg ?: "")
                }
            }
        }
        LoadingState.SUCCESS -> {
            goHome()
        }
        else -> {}
    }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
                viewModel.intent(AuthScreenEvent.SignWithCredential(credential))
            } catch (e: ApiException) {
                Log.w("TAG", "Google sign in failed", e)
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
            if (state.loadingState is LoadingState.LOADING) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                Spacer(modifier = Modifier.height(50.dp))
                AuthHeader(appName = appName)
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    onClick = {
                        viewModel.intent(AuthScreenEvent.StartWithoutSignIn)
                        goHome()
                    },
                ) {
                    Text(text = "Start Without Signing In")
                }
                GoogleSigningButton { launcher.launch(googleSignInClient.signInIntent) }
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 6.sp,
                    fontWeight = FontWeight.Light,
                    text = "Login with"
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 6.sp,
                    fontWeight = FontWeight.Light,
                    text = "By continuing, you agree to the Digital Designs Terms Of Service and Privacy Policy"
                )
            }
        )
    }
}
