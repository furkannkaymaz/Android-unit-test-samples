package com.furkan.test_codes.login_test

import kotlinx.coroutines.flow.Flow

/* when connected to service also need repository */

interface LoginUseCase {
    suspend fun invoke(username : String, password: String, confirmPassword : String) : Flow<Boolean>
}