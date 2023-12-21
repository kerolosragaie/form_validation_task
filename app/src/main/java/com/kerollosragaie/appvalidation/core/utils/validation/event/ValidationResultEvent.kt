package com.kerollosragaie.appvalidation.core.utils.validation.event

sealed class ValidationResultEvent {
    object Success : ValidationResultEvent()
    object Failure : ValidationResultEvent()
}