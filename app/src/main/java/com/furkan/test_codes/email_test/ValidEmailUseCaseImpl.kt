
package com.furkan.test_codes.email_test

class ValidEmailUseCaseImpl(
    private val validator: EmailPatternValidator
) : ValidEmailUseCase {
    override fun execute(email: String): Boolean {
        if (email.isBlank()) {
            return false
        }
        return validator.isValidEmail(email)
    }
}