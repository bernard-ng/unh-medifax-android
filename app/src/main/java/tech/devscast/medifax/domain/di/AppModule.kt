package tech.devscast.medifax.domain.di

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import tech.devscast.medifax.data.remote.ApiServiceImpl
import tech.devscast.medifax.domain.ApiService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext appContext: Context): SharedPreferences {
        return appContext.getSharedPreferences("medifax", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideApiService(client: HttpClient, preferences: SharedPreferences): ApiService {
        return ApiServiceImpl(client, preferences)
    }

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
            install(Logging) {
                logger = object: Logger {
                    override fun log(message: String) {
                        Log.i("KTOR", message)
                    }
                }
                level = LogLevel.ALL
                filter { request ->
                    request.url.host.contains("medifax.devscast.tech")
                }
                sanitizeHeader { header -> header == HttpHeaders.Authorization }
            }
        }
    }
}
