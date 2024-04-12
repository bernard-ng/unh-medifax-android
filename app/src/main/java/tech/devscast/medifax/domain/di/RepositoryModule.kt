package tech.devscast.medifax.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.devscast.medifax.data.repository.AppointmentRepositoryImpl
import tech.devscast.medifax.data.repository.DataStoreRepositoryImpl
import tech.devscast.medifax.data.repository.DoctorRepositoryImpl
import tech.devscast.medifax.data.repository.PatientRepositoryImpl
import tech.devscast.medifax.domain.repository.AppointmentRepository
import tech.devscast.medifax.domain.repository.DataStoreRepository
import tech.devscast.medifax.domain.repository.DoctorRepository
import tech.devscast.medifax.domain.repository.PatientRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAppointmentRepository(
        appointmentRepository: AppointmentRepositoryImpl
    ): AppointmentRepository

    @Binds
    @Singleton
    abstract fun bindDataStoreRepository(
        dataStoreRepository: DataStoreRepositoryImpl
    ): DataStoreRepository

    @Binds
    @Singleton
    abstract fun bindDoctorRepository(
        doctorRepository: DoctorRepositoryImpl
    ): DoctorRepository

    @Binds
    @Singleton
    abstract fun bindPatientRepository(
        patientRepository: PatientRepositoryImpl
    ): PatientRepository
}
