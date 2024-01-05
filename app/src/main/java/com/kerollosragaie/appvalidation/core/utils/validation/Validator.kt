package com.kerollosragaie.appvalidation.core.utils.validation

import android.util.Patterns
import com.kerollosragaie.appvalidation.R
import com.kerollosragaie.appvalidation.core.utils.validation.enums.PasswordValidationType
import com.kerollosragaie.appvalidation.core.utils.validation.interfaces.Validation
import java.util.regex.Pattern

class Validator : Validation {

    override fun handlePasswordValidation(
        text: String,
        passwordValidationType: PasswordValidationType
    ): Boolean =
        when (passwordValidationType) {
            PasswordValidationType.AT_LEAST_8 -> text.length >= 8
            PasswordValidationType.AT_LEAST_ONE_SPECIAL_CHAR -> {
                Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]")
                    .matcher(text).find()
            }
            PasswordValidationType.AT_LEAST_ONE_LETTER_AND_DIGIT -> {
                text.any { it.isDigit() } && text.any { it.isLetter() }
            }
        }

    override fun handleMobileValidation(
        text: String,
    ): Int? = if (text.isBlank()) {
        R.string.the_field_can_not_be_blank
    } else if (text.length != 11) {
        R.string.must_be_11_digits
    } else if (!Patterns.PHONE.matcher(text).matches()) {
        R.string.that_is_not_a_valid_number
    } else {
        null
    }

    override fun handleNameValidation(
        text: String,
    ): Int? = if (text.isBlank()) {
        R.string.the_field_can_not_be_blank
    } else {
        null
    }
}