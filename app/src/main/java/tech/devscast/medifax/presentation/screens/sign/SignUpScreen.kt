package tech.devscast.medifax.presentation.screens.sign

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import tech.devscast.medifax.presentation.screens.sign.vaidables.LengthValidable
import tech.devscast.medifax.presentation.theme.MedifaxTheme
import tech.devscast.medifax.presentation.viewmodel.SignUpViewModel
import tech.devscast.medifax.presentation.viewmodel.SignUpViewState
import tech.devscast.validable.EmailValidable
import tech.devscast.validable.NotEmptyValidable
import tech.devscast.validable.withValidable

@Composable
fun SignUpScreen(
    onSignInClicked: () -> Unit,
    onSignUpCompleted: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val email = remember { EmailValidable() }
    val name = remember { NotEmptyValidable() }
    val password = remember { LengthValidable(8) }
    var checked by remember { mutableStateOf(false) }
    var isPasswordVisible by remember { mutableStateOf(false) }

    val uiState = viewModel.uiState


    Scaffold { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeadingTitle("Inscription", modifier = Modifier.align(Alignment.Start))

            SignUpNameField(name, uiState)
            SignUpEmailField(email, uiState)
            SignUpPasswordField(password, isPasswordVisible, { isPasswordVisible = !isPasswordVisible }, uiState)
            SignUpTermsField(checked, { checked = it }, uiState)

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
                uiState.patient != null -> { onSignUpCompleted() }
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
                        withValidable(name, password, email) {
                            viewModel.register(email.value, password.value, name.value)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    enabled = !uiState.isLoading
                ) {
                    when {
                        uiState.isLoading -> {
                        Text(
                            text = "Inscription...",
                            fontSize = 17.sp,
                            modifier = Modifier.padding(6.dp)
                        )
                    }
                        else -> {
                        Text(
                            text = "S'inscrire",
                            fontSize = 17.sp,
                            modifier = Modifier.padding(6.dp)
                        )
                    }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                FilledTonalButton(
                    onClick = { onSignInClicked() },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    enabled = !uiState.isLoading
                ) {
                    Text(
                        text = "Se connecter",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(6.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun SignUpTermsField(checked: Boolean, onChecked: (Boolean) -> Unit, uiState: SignUpViewState) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checked,
            onCheckedChange = { onChecked(it) },
            enabled = !uiState.isLoading
        )
        Text(
            text = "J'accepte les terms et condifitions d'utilisations",
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
private fun SignUpPasswordField(
    password: LengthValidable,
    visible: Boolean,
    onTogglePassword: () -> Unit,
    uiState: SignUpViewState
) {
    PasswordField(
        password.value,
        visible,
        onValueChange = { password.value = it },
        onTogglePassword = onTogglePassword,
        enabled = !uiState.isLoading,
        isError = password.hasError()
    )
    AnimatedVisibility(visible = password.hasError()) {
        password.errorMessage?.let {
            Text(
                text = it,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.error,
                fontWeight = FontWeight.Bold
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
private fun SignUpEmailField(
    email: EmailValidable,
    uiState: SignUpViewState
) {
    EmailField(
        email.value,
        onValueChange = { email.value = it },
        isError = email.hasError(),
        enabled = !uiState.isLoading
    )
    AnimatedVisibility(visible = email.hasError()) {
        email.errorMessage?.let {
            Text(
                text = it,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.error,
                fontWeight = FontWeight.Bold
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
private fun SignUpNameField(
    name: NotEmptyValidable,
    uiState: SignUpViewState
) {
    OutlinedTextField(
        value = name.value,
        onValueChange = { name.value = it },
        label = { Text(text = "Nom complet") },
        leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        isError = name.hasError(),
        enabled = !uiState.isLoading
    )
    AnimatedVisibility(visible = name.hasError()) {
        name.errorMessage?.let {
            Text(
                text = it,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.error,
                fontWeight = FontWeight.Bold
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Preview(showBackground = true, device = "id:pixel_8_pro", showSystemUi = true)
@Composable
fun PreviewSignUp() {
    MedifaxTheme {
        SignUpScreen({}, {})
    }
}

@Preview(showBackground = true, device = "id:pixel_8_pro", showSystemUi = true)
@Composable
fun PreviewSignUpDark() {
    MedifaxTheme(darkTheme = true) {
        Surface {
            SignUpScreen({}, {})
        }
    }
}
