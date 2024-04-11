package tech.devscast.medifax.di


import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import tech.devscast.medifax.data.remote.ApiService
import tech.devscast.medifax.data.remote.ApiServiceImpl
import tech.devscast.medifax.data.repository.DataStoreRepository

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideSharedPreference(@ApplicationContext appContext: Context): SharedPreferences {
        return appContext.getSharedPreferences("onboarding", Context.MODE_PRIVATE)
    }

    @Provides
    fun provideApiService(): ApiService {
        return ApiService.create()
    }

    @Provides
    fun provideDataStoreRepository(
        @ApplicationContext appContext: Context
    ) : DataStoreRepository {
        return DataStoreRepository(context = appContext)
    }
}
