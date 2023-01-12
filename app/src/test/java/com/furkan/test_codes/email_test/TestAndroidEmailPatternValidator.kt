package com.furkan.test_codes.email_test

import androidx.core.util.PatternsCompat

class TestAndroidEmailPatternValidator : EmailPatternValidator {
    override fun isValidEmail(email: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }
}