package tech.devscast.medifax.ui.screens.sign

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.devscast.medifax.ui.screens.sign.components.EmailField
import tech.devscast.medifax.ui.screens.sign.components.HeadingTitle
import tech.devscast.medifax.ui.screens.sign.components.PasswordField
import tech.devscast.medifax.ui.theme.MedifaxTheme
import tech.devscast.medifax.ui.theme.poppinsFontFamily

@Composable
fun SignInScreen(onSignUpClicked: () -> Unit, onSignInCompleted: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeadingTitle("Connexion", modifier = Modifier.align(Alignment.Start))

        EmailField(email, onValueChange = { email = it })
        Spacer(modifier = Modifier.height(16.dp))
        PasswordField(
            password,
            isPasswordVisible,
            onValueChange = { password = it },
            onTogglePassword = { isPasswordVisible = !isPasswordVisible }
        )
        Spacer(modifier = Modifier.weight(1f))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(align = Alignment.BottomCenter)
        ) {
            Button(
                onClick = { onSignInCompleted() },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = "Se connecter",
                    fontSize = 17.sp,
                    modifier = Modifier.padding(6.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            FilledTonalButton(
                onClick = { onSignUpClicked() },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
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