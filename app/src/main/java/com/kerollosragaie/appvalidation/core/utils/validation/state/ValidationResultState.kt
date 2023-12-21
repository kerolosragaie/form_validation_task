package com.kerollosragaie.appvalidation.core.utils.validation.state

import androidx.annotation.StringRes

data class ValidationResultState(
    val isValid: Boolean,
    @StringRes var errorMessageId: Int? = null,
)