package com.kerollosragaie.appvalidation.core.utils.validation.usecase

import com.kerollosragaie.appvalidation.R
import com.kerollosragaie.appvalidation.core.utils.validation.interfaces.Validator
import com.kerollosragaie.appvalidation.core.utils.validation.state.ValidationResultState

internal class ValidateText : Validator {
    override fun execute(text: String): ValidationResultState {
        val validationResultState by lazy { ValidationResultState(isValid = false) }

        return if (text.isBlank()) {
            validationResultState.copy(
                isValid = false,
                errorMessageId = R.string.the_field_can_not_be_blank,
            )
        } else {
            validationResultState.copy(isValid = true)
        }
    }
}