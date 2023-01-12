package com.furkan.test_codes.email_test

import android.util.Patterns

class AndroidEmailPatternValidator() : EmailPatternValidator {
    override fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}