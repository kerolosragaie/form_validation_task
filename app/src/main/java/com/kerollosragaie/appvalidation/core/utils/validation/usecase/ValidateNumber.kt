package com.kerollosragaie.appvalidation.core.utils.validation.usecase

import android.util.Patterns
import com.kerollosragaie.appvalidation.R
import com.kerollosragaie.appvalidation.core.utils.validation.interfaces.Validator
import com.kerollosragaie.appvalidation.core.utils.validation.state.ValidationResultState

internal class ValidateNumber : Validator {
    override fun execute(text: String): ValidationResultState {
        val validationResultState by lazy { ValidationResultState(isValid = false) }

        return if (text.isBlank()) {
            validationResultState.copy(
                isValid = false,
                errorMessageId = R.string.the_field_can_not_be_blank,
            )
        } else if (text.length != 11) {
            validationResultState.copy(
                isValid = false,
                errorMessageId = R.string.must_be_11_digits
            )
        } else if (!Patterns.PHONE.matcher(text).matches()) {
            validationResultState.copy(
                isValid = false,
                errorMessageId = R.string.that_is_not_a_valid_number
            )
        } else {
            validationResultState.copy(isValid = true)
        }
    }
}