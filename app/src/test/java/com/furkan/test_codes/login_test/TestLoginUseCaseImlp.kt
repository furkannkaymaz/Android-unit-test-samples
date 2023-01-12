package com.furkan.test_codes.login_test

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TestLoginUseCaseImlp : LoginUseCase {

    override suspend fun invoke(username: String, password: String, confirmPassword: String) : Flow<Boolean> = flow {
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            emit(false)
        } else if (password != confirmPassword) {
            emit(false)
        } else if (password.length < 6 && confirmPassword.length < 6) {
            emit(false)
        }
        emit(true)
    }
}