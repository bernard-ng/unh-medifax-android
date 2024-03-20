package tech.devscast.medifax.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.devscast.medifax.R
import tech.devscast.medifax.ui.theme.MedifaxTheme
import tech.devscast.medifax.ui.theme.poppinsFontFamily

@Composable
fun SignIn(){
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "SIGN IN",
                fontWeight = FontWeight.Medium,
                fontFamily = poppinsFontFamily,
                fontSize = 30.sp,
                color = Color(0xFF223A6A)
            )
            Spacer(modifier = Modifier.height(50.dp))
            Surface{
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                    TextField(
                        value = email.value,
                        onValueChange = { email.value = it },
                        label = {
                            Row (verticalAlignment = Alignment.CenterVertically){
                                androidx.compose.foundation.Image(
                                    painter = painterResource(id = R.drawable.email),
                                    contentDescription = "Email Icon",
                                    modifier = Modifier.size(24.dp)
                                )
                                Text("    Enter your email")

                            }
                        },
                        modifier = Modifier.width(350.dp)

                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    TextField(
                        value = password.value,
                        onValueChange = { password.value = it },
                        label = {
                            Row (verticalAlignment = Alignment.CenterVertically){
                                androidx.compose.foundation.Image(
                                    painter = painterResource(id = R.drawable.icons8_password),
                                    contentDescription = "Password Icon"
                                )
                                Text("    Enter your password")
                                Spacer(modifier = Modifier.weight(1f))
                                androidx.compose.foundation.Image(
                                    painter = painterResource(id = R.drawable.eye_slash__1_),
                                    contentDescription = "Eye slash Icon"

                                )

                            }
                        },
                        modifier = Modifier.width(350.dp),

                        )

                }

            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Forgot password?",
                color = Color.Blue,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(bottom = 16.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))

            Surface (modifier = Modifier.width(300.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(
                        onClick = {},
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Text(
                            text = "SIGN IN",
                            fontSize = 17.sp,
                            modifier = Modifier.padding(6.dp)
                        )
                    }

                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row (verticalAlignment = Alignment.CenterVertically){
                Text(
                    text = "Don't have an account ? "
                )
                Text(
                    text = " Sign Up",
                    color = Color.Blue
                    )

            }
            Spacer(modifier = Modifier.height(55.dp))
            Text(
                text = "OR"
            )
            Spacer(modifier = Modifier.height(45.dp))

            Surface (modifier = Modifier.width(300.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(
                        onClick = {},
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.icons8_google),
                            contentDescription = "Google sign in",
                            modifier = Modifier.size(16.dp)
                        )
                        Text(text = "    Sign in with Google")
                    }

                }
            }




        }
    }

}

@Preview(showBackground = true, device = "id:pixel_8_pro", showSystemUi = true)
@Composable
fun PreviewSignIn() {
    MedifaxTheme {
        SignIn()
    }
}