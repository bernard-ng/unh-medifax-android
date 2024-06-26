package tech.devscast.medifax.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import tech.devscast.medifax.R
import tech.devscast.medifax.presentation.theme.MedifaxTheme
import tech.devscast.medifax.presentation.theme.poppinsFontFamily
import tech.devscast.medifax.presentation.viewmodel.GetStartedViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetStartedScreen(
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
    viewModel: GetStartedViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val modelBottomSheetSate = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.medifax_icon),
                contentDescription = "Healthcare Logo",
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Medifax",
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFontFamily,
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(32.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Bienvenue !",
                    fontWeight = FontWeight.Black,
                    fontFamily = poppinsFontFamily,
                    fontSize = 32.sp,
                )
                Text(
                    text = "Connectez-vous pour rester en bonne santé et en forme",
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(
                    onClick = { showBottomSheet = true },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = "Commencer",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(6.dp)
                    )
                }
            }
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = modelBottomSheetSate,
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    Button(
                        onClick = {
                            coroutineScope.launch { modelBottomSheetSate.hide() }
                                .invokeOnCompletion {
                                    if (!modelBottomSheetSate.isVisible) {
                                        showBottomSheet = false
                                    }
                                    onSignInClick()
                                }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text(
                            text = "Se connecter",
                            fontSize = 16.sp,
                            modifier = Modifier.padding(6.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    FilledTonalButton(
                        onClick = {
                            coroutineScope.launch { modelBottomSheetSate.hide() }
                                .invokeOnCompletion {
                                    if (!modelBottomSheetSate.isVisible) {
                                        showBottomSheet = false
                                    }
                                    onSignUpClick()
                                }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text(
                            text = "S'inscrire",
                            fontSize = 16.sp,
                            modifier = Modifier.padding(6.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_8_pro", showSystemUi = true)
@Composable
fun PreviewGetStartedScreen() {
    MedifaxTheme(darkTheme = false) {
        GetStartedScreen({}, {})
    }
}

@Preview(showBackground = true, device = "id:pixel_8_pro", showSystemUi = true)
@Composable
fun PreviewGetStartedScreenDark() {
    MedifaxTheme(darkTheme = true) {
        Surface {
            GetStartedScreen({}, {})
        }
    }
}
