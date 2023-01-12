
package com.furkan.test_codes.email_test

/* Use EmailPatternValidator for avoid using Android import in usecase */

interface EmailPatternValidator {
    fun isValidEmail(email: String): Boolean
}