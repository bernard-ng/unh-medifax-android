package tech.devscast.medifax.presentation.screens.sign

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import tech.devscast.medifax.presentation.screens.sign.components.EmailField
import tech.devscast.medifax.presentation.screens.sign.components.HeadingTitle
import tech.devscast.medifax.presentation.screens.sign.components.PasswordField
import tech.devscast.medifax.presentation.theme.MedifaxTheme
import tech.devscast.medifax.presentation.viewmodel.SignInViewModel
import tech.devscast.validable.EmailValidable
import tech.devscast.validable.withValidable

@Composable
fun SignInScreen(
    onSignUpClicked: () -> Unit,
    onSignInCompleted: () -> Unit,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val email = remember { EmailValidable() }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    val uiState = viewModel.uiState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeadingTitle("Connexion", modifier = Modifier.align(Alignment.Start))

        EmailField(
            email.value,
            onValueChange = { email.value = it },
            enabled = !uiState.isLoading,
            isError = email.hasError()
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordField(
            password,
            isPasswordVisible,
            onValueChange = { password = it },
            onTogglePassword = { isPasswordVisible = !isPasswordVisible },
            enabled = !uiState.isLoading
        )
        Spacer(modifier = Modifier.height(16.dp))

        when {
            uiState.errorMessage != null -> {
                AnimatedVisibility(visible = true) {
                    Text(
                        text = uiState.errorMessage.toString(),
                        color = MaterialTheme.colorScheme.error,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            uiState.token != null -> { onSignInCompleted() }
        }

        Spacer(modifier = Modifier.weight(1f))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(align = Alignment.BottomCenter)
        ) {
            Button(
                onClick = {
                    withValidable(email) {
                        viewModel.login(email.value, password)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                enabled = !uiState.isLoading
            ) {
                when {
                    uiState.isLoading -> {
                        Text(
                            text = "Connexion...",
                            fontSize = 17.sp,
                            modifier = Modifier.padding(6.dp)
                        )
                    }
                    else -> {
                        Text(
                            text = "Se connecter",
                            fontSize = 17.sp,
                            modifier = Modifier.padding(6.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            FilledTonalButton(
                onClick = { onSignUpClicked() },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                enabled = !uiState.isLoading
            ) {
                Text(
                    text = "S'inscrire",
                    fontSize = 17.sp,
                    modifier = Modifier.padding(6.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_8_pro", showSystemUi = true)
@Composable
fun PreviewSignIn() {
    MedifaxTheme {
        SignInScreen({}, {})
    }
}

@Preview(showBackground = true, device = "id:pixel_8_pro", showSystemUi = true)
@Composable
fun PreviewSignInDark() {
    MedifaxTheme (darkTheme = true) {
        Surface {
            SignInScreen({}, {})
        }
    }
}