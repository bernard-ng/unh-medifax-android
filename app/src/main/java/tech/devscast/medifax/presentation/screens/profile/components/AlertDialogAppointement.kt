package tech.devscast.medifax.presentation.screens.profile.components

import androidx.compose.foundation.layout.*
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material3.Button
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp



@Composable
fun ConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    Dialog(onDismissRequest = onDismiss) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text(text = "Confirmation") },
            text = { Text("Êtes-vous sûr de vouloir prendre ce rendez-vous ?") },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirm()
                        onDismiss()
                        Toast.makeText(context, "Rendez-vous confirmé !", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Text("Confirmer")
                }
            },
            dismissButton = {
                Button(
                    onClick = { onDismiss() }
                ) {
                    Text("Annuler")
                }
            }
        )
    }
}

@Composable
fun AppointmentScreen(showDialog: () -> Unit) {
    var showDialogState by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showDialogState) {
            ConfirmationDialog(
                onConfirm = {
                    println("Rendez-vous confirmé !")
                },
                onDismiss = { showDialogState = false }
            )
        }
    }
}




