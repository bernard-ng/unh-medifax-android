package tech.devscast.medifax.ui.screens

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
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.devscast.medifax.R
import tech.devscast.medifax.ui.theme.MedifaxTheme
import tech.devscast.medifax.ui.theme.poppinsFontFamily

@Composable
fun SignUpScreen(){
    val email = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val checkedState = remember { mutableStateOf(false) }
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
                text = "SIGN UP",
                fontWeight = FontWeight.Medium,
                fontFamily = poppinsFontFamily,
                fontSize = 30.sp,
                color = Color(0xFF223A6A)
            )
            Spacer(modifier = Modifier.height(50.dp))
            Surface{
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                    TextField(
                        value = name.value,
                        onValueChange = { name.value = it },
                        label = {
                            Row (verticalAlignment = Alignment.CenterVertically){
                                androidx.compose.foundation.Image(
                                    painter = painterResource(id = R.drawable.person_male_svgrepo_com),
                                    contentDescription = "Person Icon",
                                    modifier = Modifier.size(24.dp)
                                )
                                Text("    Enter your name")

                            }
                        },
                        modifier = Modifier.width(350.dp)

                    )
                    Spacer(modifier = Modifier.height(20.dp))
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

                    Spacer(modifier = Modifier.height(20.dp))


                }

            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(verticalArrangement = Arrangement.Center) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = checkedState.value,
                        onCheckedChange = { checkedState.value = it }
                    )
                    Text(
                        text = buildAnnotatedString {
                            append("I agree to the healthcare ")
                            withStyle(style = SpanStyle(color = Color.Blue)) {
                                append("Terms of")
                            }
                        },
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )

                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "          Service",
                        color = Color.Blue
                    )
                    Text(
                        text = " and"
                    )
                    Text(
                        text = " Privacy Policy",
                        color = Color.Blue
                    )
                }
            }

            Spacer(modifier = Modifier.height(185.dp))

            Surface (modifier = Modifier.width(300.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(
                        onClick = {},
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Text(
                            text = "SIGN UP",
                            fontSize = 17.sp,
                            modifier = Modifier.padding(6.dp)
                        )
                    }

                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row (verticalAlignment = Alignment.CenterVertically){
                Text(
                    text = "Already have an account ? "
                )
                Text(
                    text = " Sign In",
                    color = Color.Blue
                )

            }



        }
    }

}

@Preview(showBackground = true, device = "id:pixel_8_pro", showSystemUi = true)
@Composable
fun PreviewSignUp() {
    MedifaxTheme {
        SignUpScreen()
    }
}