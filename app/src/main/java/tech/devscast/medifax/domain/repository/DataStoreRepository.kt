package tech.devscast.medifax.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun saveJwtToken(token: String);
    suspend fun readJwtToken(): Flow<String>;
    suspend fun saveOnBoardingState(completed: Boolean);
    fun readOnBoardingState(): Flow<Boolean>;
}
