package com.example.store.features.authentication

import com.example.store.core.testing.fake_repositories.FakeAccountRepository
import com.example.store.features.authentication.login.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class LoginViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var accountRepository: FakeAccountRepository
    private lateinit var viewModel: LoginViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        accountRepository = FakeAccountRepository()
        viewModel = LoginViewModel(accountRepository)
    }

    @Test
    fun `isLoggedIn is true when logged in successfully`() = runTest {
        viewModel.login("example@example.com", "password")

        assertTrue(viewModel.isLoggedIn)
    }

    @Test
    fun `isLoggedIn is false when login fails`() = runTest {
        accountRepository.setShouldFail(true)
        viewModel.login("example@example.com", "password")

        assertFalse(viewModel.isLoggedIn)
    }

    @Test
    fun `isLoggedIn is false by default`() = runTest {
        assertFalse(viewModel.isLoggedIn)

    }

    @Test
    fun `message should be false by default`() = runTest {
        assertNull(viewModel.message.value)
    }

    @Test
    fun `message should not be null when an error occurs`() = runTest {
        accountRepository.setShouldFail(true)

        viewModel.login("example@example.com", "password")

        assertNotNull(viewModel.message.value)
    }

    @Test
    fun `message should be null when login is successful`() = runTest {
        viewModel.login("example@example.com", "password")

        assertNull(viewModel.message.value)
    }

    @Test
    fun `message should be null when message shown`() = runTest {
        accountRepository.setShouldFail(true)

        viewModel.login("example@example.com", "password")

        viewModel.messageShown()

        assertNull(viewModel.message.value)
    }

    @Test
    fun `isLoggedIn remains false when email or password is empty`() = runTest {
        viewModel.login("", "")

        assertFalse(viewModel.isLoggedIn)
        assertNotNull(viewModel.message.value)
    }

    @Test
    fun `correct message is shown when email or password is empty`() = runTest {
        viewModel.login("", "")

        assertEquals("Por favor, preencha todos os campos", viewModel.message.value)
    }

}