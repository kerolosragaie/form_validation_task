package com.kerollosragaie.appvalidation.core.utils.validation.interfaces

import com.kerollosragaie.appvalidation.core.utils.validation.enums.PasswordValidationType

internal interface Validation {
    fun handlePasswordValidation(
        text: String,
        passwordValidationType: PasswordValidationType
    ): Boolean

    fun handleMobileValidation(
        text: String,
    ): Int?

    fun handleNameValidation(
        text: String,
    ): Int?
}