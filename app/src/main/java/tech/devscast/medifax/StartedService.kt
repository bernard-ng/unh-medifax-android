package tech.devscast.medifax

import android.app.Notification
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

class ScheduleCheckService : Service() {
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private var isRunning = false
    private var previousSchedule: String by mutableStateOf("Emploi du temps actuel")

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        handler = Handler()
        startScheduleCheck()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun startScheduleCheck() {
        isRunning = true
        runnable = Runnable {

            val currentSchedule = checkSchedule()
            if (currentSchedule != previousSchedule) {
                showNotification("Changement dans l'emploi du temps", "Nouvelle mise à jour de l'emploi du temps détectée.")
                previousSchedule = currentSchedule
            }
            if (isRunning) {
                handler.postDelayed(runnable, CHECK_INTERVAL)
            }
        }
        handler.post(runnable)
    }

    private fun checkSchedule(): String {

        return "Emploi du temps actuel"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showNotification(title: String, content: String) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "ScheduleCheckServiceChannel"
        val notificationBuilder = Notification.Builder(this, channelId)
            .setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
        val notification = notificationBuilder.build()
        notificationManager.notify(1, notification)
    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
        handler.removeCallbacks(runnable)
    }

    companion object {
        private const val CHECK_INTERVAL: Long = 60000
    }
}

@Composable
fun NotificationPreview(
    previousSchedule: String,
    showNotification: (String, String) -> Unit,
    updateSchedule: (String) -> Unit
) {
    var currentTitle by remember { mutableStateOf("Changement dans l'emploi du temps") }
    var currentContent by remember { mutableStateOf("Saisissez ici vos changements d'horaire.") }
    var currentSchedule by remember { mutableStateOf(previousSchedule) }

    Column {
        Text(text = "Emploi du temps actuel : $currentSchedule")

        TextField(
            value = currentContent,
            onValueChange = { currentContent = it },
            label = { Text("Nouveau contenu de la notification") }
        )

        Button(onClick = {
            
            currentSchedule = "$currentTitle - $currentContent"

            updateSchedule(currentSchedule)
        }) {
            Text(text = "Mettre à jour l'emploi du temps")
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_8_pro", showSystemUi = true)
@Composable
fun NotificationPreviewPreview() {
    val previousSchedule = "Emploi du temps actuel"
    NotificationPreview(previousSchedule, { title, content ->  }) { updatedSchedule -> }
}
