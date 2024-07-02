package tech.devscast.medifax.presentation.screens.doctor.components

import androidx.compose.ui.window.Dialog
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.material3.Button
import android.widget.Toast
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext

@Composable
fun AppointmentConfirmationDialog(
    date: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    Dialog(onDismissRequest = onDismiss) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text(text = "Confirmation") },
            text = { Text("Prendre rendez-vous pour le ${date} ?") },
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
                Button(onClick = { onDismiss() }) {
                    Text("Annuler")
                }
            }
        )
    }
}
