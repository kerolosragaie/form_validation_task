package com.kerollosragaie.appvalidation.core.utils.validation.usecase

import com.kerollosragaie.appvalidation.R
import com.kerollosragaie.appvalidation.core.utils.validation.interfaces.Validator
import com.kerollosragaie.appvalidation.core.utils.validation.state.ValidationResultState
import java.util.regex.Pattern

class ValidatePassword : Validator {
    override fun execute(text: String): ValidationResultState {
        val isContainLettersAndDigits = text.any { it.isDigit() } && text.any { it.isLetter() }
        val specialChar: Pattern = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]")

        return if (text.isBlank()) {
            ValidationResultState(
                isValid = false,
                errorMessageId = R.string.the_field_can_not_be_blank
            )
        } else if (text.length < 8) {
            ValidationResultState(
                isValid = false,
                errorMessageId = R.string.the_password_needs_to_consist_of_at_least_8_characters
            )
        }else if (!specialChar.matcher(text).find()) {
            ValidationResultState(
                isValid = false,
                errorMessageId = R.string.the_password_should_contain_at_least_special_character
            )
        }  else if (!isContainLettersAndDigits) {
            ValidationResultState(
                isValid = false,
                errorMessageId = R.string.the_password_needs_to_contain_at_least_one_letter_and_digit
            )
        } else {
            ValidationResultState(isValid = true)
        }
    }
}