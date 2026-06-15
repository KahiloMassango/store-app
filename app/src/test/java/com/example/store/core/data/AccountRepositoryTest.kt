package com.example.store.core.data

import com.example.store.core.data.repository.AccountRepository
import com.example.store.core.model.user.User
import com.example.store.core.network.model.user.UserDtoRes
import com.example.store.core.network.model.user.asExternalModel
import com.example.store.core.testing.fake_data.fakeUser1
import com.example.store.core.testing.fake_datasources.FakeJwtLocalDataSource
import com.example.store.core.testing.fake_datasources.FakeUserLocalDataSource
import com.example.store.core.testing.fake_datasources.FakeUserNetworkDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AccountRepositoryTest {

    private lateinit var networkDataSource: FakeUserNetworkDataSource
    private lateinit var localDataSource: FakeUserLocalDataSource
    private lateinit var jwtLocalDataSource: FakeJwtLocalDataSource
    private lateinit var repository: AccountRepository

    @Before
    fun setup() {
        networkDataSource = FakeUserNetworkDataSource()
        localDataSource = FakeUserLocalDataSource()
        jwtLocalDataSource = FakeJwtLocalDataSource()

        repository = AccountRepositoryImpl(networkDataSource, localDataSource, jwtLocalDataSource)
    }

    @Test
    fun `isUserLoggedIn should return correct state`() = runTest {
        val result = repository.isUserLoggedIn().first()
        assertFalse(result)
    }


    @Test
    fun `isUserLoggedIn returns false when user logout`() = runTest {
        repository.createAccount(
            username = fakeUser1.username,
            email = fakeUser1.email,
            password = fakeUser1.username,
            phoneNumber = fakeUser1.phoneNumber
        )
        repository.login(fakeUser1.email, fakeUser1.username)
        repository.logout()

        val result = repository.isUserLoggedIn().first()

        assertFalse(result)
    }

    @Test
    fun `login should save tokens and user details when successful`() = runTest {
        repository.createAccount(
            username = fakeUser1.username,
            email = fakeUser1.email,
            password = fakeUser1.username,
            phoneNumber = fakeUser1.phoneNumber
        )
        val result = repository.login(fakeUser1.email, fakeUser1.username)

        assertTrue(result.isSuccess)
        assertEquals(repository.getLocalUserDetails(), fakeUser1)
    }

    @Test
    fun `logout should clear tokens and user details`() = runTest {
        repository.createAccount(
            username = fakeUser1.username,
            email = fakeUser1.email,
            password = fakeUser1.username,
            phoneNumber = fakeUser1.phoneNumber
        )
        repository.login(fakeUser1.email, fakeUser1.username)

        val result = repository.logout()

        assertTrue(result.isSuccess)
        assertFalse(repository.isUserLoggedIn().first())
        assertEquals(User("", "",""), repository.getLocalUserDetails())
    }

    @Test
    fun `updateUser should update and save user details`() = runTest {
        val updatedUser = UserDtoRes("UpdatedUser", "new@example.com", "987654321")
        repository.createAccount(
            username = fakeUser1.username,
            email = fakeUser1.email,
            password = fakeUser1.username,
            phoneNumber = fakeUser1.phoneNumber
        )
        repository.login(fakeUser1.email, fakeUser1.username)

        val result = repository.updateUser(updatedUser.username, updatedUser.email, updatedUser.phoneNumber)
        assertTrue(result.isSuccess)
        assertEquals(updatedUser.asExternalModel(), repository.getLocalUserDetails())
    }

    @Test
    fun `login returns failure when credentials are invalid`() = runTest {
        val result = repository.login("invalid_email", "invalid_password")

        assertTrue(result.isFailure)
    }

    @Test
    fun `fetchAccountDetails should return user details when logged in`() = runTest {
        repository.createAccount(
            username = fakeUser1.username,
            email = fakeUser1.email,
            password = fakeUser1.username,
            phoneNumber = fakeUser1.phoneNumber
        )
        repository.login(fakeUser1.email, fakeUser1.username)

        val result = repository.fetchAccountDetails()

        assertTrue(result.isSuccess)
        assertEquals(fakeUser1, result.getOrThrow())
    }

    @Test
    fun `saveLocalUserDetails should save user details`() = runTest {
        repository.saveLocalUserDetails(fakeUser1)

        assertEquals(fakeUser1, repository.getLocalUserDetails())
    }


}