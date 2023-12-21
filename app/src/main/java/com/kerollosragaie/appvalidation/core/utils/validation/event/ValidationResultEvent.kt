package com.kerollosragaie.appvalidation.core.utils.validation.event

sealed class ValidationResultEvent {
    data object Success : ValidationResultEvent()
}