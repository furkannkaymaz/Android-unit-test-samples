package com.furkan.test_codes.email_test

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ValidEmailUseCaseTest {

    private lateinit var repository : TestAndroidEmailPatternValidator
    private lateinit var useCaseImpl: ValidEmailUseCaseImpl

    @Before
    fun setUp(){
        repository = TestAndroidEmailPatternValidator()
        useCaseImpl = ValidEmailUseCaseImpl(repository)
    }

    @Test
    fun `is valid email return true`() = runBlocking {
        val email = useCaseImpl.execute("furkannkaymaz@gmail.com")
        Assert.assertTrue(email)
    }

    @Test
    fun `is invalid email return false`() = runBlocking {
        val email = useCaseImpl.execute("furkannkaymazdeneme")
        Assert.assertFalse(email)
    }
    @Test
    fun `is email empty return false`() = runBlocking {
        val email = useCaseImpl.execute("")
        Assert.assertFalse(email)
    }

}