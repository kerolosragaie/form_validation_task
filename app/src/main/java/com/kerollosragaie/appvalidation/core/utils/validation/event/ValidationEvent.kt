package com.kerollosragaie.appvalidation.core.utils.validation.event

import com.kerollosragaie.appvalidation.core.utils.validation.state.ValidationState

sealed class ValidationEvent {
    object Submit : ValidationEvent()
    data class TextFieldValueChange(val validationState: ValidationState) : ValidationEvent()
}