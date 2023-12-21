package com.kerollosragaie.appvalidation.core.utils.validation.usecase

import com.kerollosragaie.appvalidation.R
import com.kerollosragaie.appvalidation.core.utils.validation.interfaces.Validator
import com.kerollosragaie.appvalidation.core.utils.validation.state.ValidationResultState

class ValidateText : Validator {
    override fun execute(text: String): ValidationResultState {
        return if (text.isBlank()) {
            ValidationResultState(
                isValid = false,
                errorMessageId = R.string.the_field_can_not_be_blank,
            )
        }else{
            ValidationResultState(isValid = true)
        }
    }
}