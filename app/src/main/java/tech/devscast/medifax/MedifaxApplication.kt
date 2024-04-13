package tech.devscast.medifax

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp

class MedifaxApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}