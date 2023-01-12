package com.furkan.test_codes.login_test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel
    private lateinit var loginUseCaseImlp: TestLoginUseCaseImlp

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        loginUseCaseImlp = TestLoginUseCaseImlp()
        viewModel = LoginViewModel(loginUseCaseImlp)
    }

    @Test
    fun `empty username return false`() = runTest(
    ) {
        val result = viewModel.login("", "123456", "123456")
        Assert.assertFalse(result.value)
    }

    @Test
    fun `password letter under six return false`() = runTest(
    ) {
        val result = viewModel.login(
            "Furkan",
            "14",
            "15",
        )
        Assert.assertFalse(result.value)
    }

    @Test
    fun `password and repassword don't match return false`() = runTest(
    ) {
        val result = viewModel.login(
            "Furkan",
            "123456789",
            "12345678910",
        )
        Assert.assertFalse(result.value)
    }


    @Test
    fun `empty password return false`() = runTest(
    ) {
        val result = viewModel.login(
            "Furkan",
            "",
            "",
        )
        Assert.assertFalse(result.value)
    }

    @Test
    fun `valid username correct password and repeat password return true`() = runTest(
        UnconfinedTestDispatcher()
    ) {
        launch {
            val result = viewModel.login(
                "Furkan",
                "123456",
                "123456",
            )
            advanceUntilIdle()
            Assert.assertTrue(result.value)
        }
    }
}