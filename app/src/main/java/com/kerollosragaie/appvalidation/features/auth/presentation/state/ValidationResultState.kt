package com.kerollosragaie.appvalidation.features.auth.presentation.state

import androidx.annotation.StringRes

data class ValidationResultState(
    val isValid: Boolean = false,
    @StringRes var errorMessageId: Int? = null,
)