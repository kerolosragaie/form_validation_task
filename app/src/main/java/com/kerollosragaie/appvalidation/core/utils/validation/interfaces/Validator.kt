package com.kerollosragaie.appvalidation.core.utils.validation.interfaces

import com.kerollosragaie.appvalidation.core.utils.validation.state.ValidationResultState

internal interface Validator {
    fun execute(text: String): ValidationResultState
}