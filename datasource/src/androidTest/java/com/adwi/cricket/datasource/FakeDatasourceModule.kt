package com.adwi.cricket.datasource

import com.adwi.cricket.datasource.repository.user.UserRepository
import com.adwi.cricket.model.User
import com.google.firebase.auth.AuthCredential
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Singleton

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatasourceModule2::class]
)
@Module
object FakeDatasourceModule {

    @Singleton
    @Provides
    fun provideFakeUserRepository() = object : UserRepository {

        private val user = User(
            id = "1",
            name = "John",
            email = "john@gmail.com",
            hasCompletedOnBoarding = false
        )

        override fun getSignedInUser(): Flow<User?> = flowOf(null)
        override suspend fun signInWithCredential(credential: AuthCredential): User = user
        override fun signOut(): User? = null
    }
}