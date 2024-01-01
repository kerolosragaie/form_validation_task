package com.kerollosragaie.appvalidation.core.utils.validation

import com.kerollosragaie.appvalidation.core.components.TextFieldType
import com.kerollosragaie.appvalidation.core.utils.validation.event.ValidationEvent
import com.kerollosragaie.appvalidation.core.utils.validation.event.ValidationResultEvent
import com.kerollosragaie.appvalidation.core.utils.validation.interfaces.TextFieldId
import com.kerollosragaie.appvalidation.core.utils.validation.state.ValidationState
import com.kerollosragaie.appvalidation.core.utils.validation.usecase.ValidateNumber
import com.kerollosragaie.appvalidation.core.utils.validation.usecase.ValidatePassword
import com.kerollosragaie.appvalidation.core.utils.validation.usecase.ValidateText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class BaseValidation {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val forms = MutableStateFlow<Map<TextFieldId, ValidationState>>(emptyMap())

    private val _validationEvent: MutableSharedFlow<ValidationResultEvent?> = MutableSharedFlow()
    val validationEvent: SharedFlow<ValidationResultEvent?> = _validationEvent

    fun onEvent(event: ValidationEvent) {
        when (event) {
            is ValidationEvent.TextFieldValueChange -> {
                val validatorType = when (event.validationState.textFieldType) {
                    TextFieldType.Number -> ValidateNumber()
                    TextFieldType.Password -> ValidatePassword()
                    TextFieldType.Text -> ValidateText()
                }
                val validationResult = validatorType.execute(event.validationState.text.trim())
                coroutineScope.launch {
                    val updatedForms = forms.value.toMutableMap()
                    updatedForms[event.validationState.id] = if (validationResult.isValid) {
                        event.validationState.copy(
                            hasError = false,
                            errorMessageId = null,
                        )
                    } else {
                        event.validationState.copy(
                            hasError = true,
                            errorMessageId = validationResult.errorMessageId,
                        )
                    }
                    forms.emit(updatedForms)
                }
            }

            is ValidationEvent.Submit -> isValidForm()
        }
    }

    private fun isValidForm() {
        var isValidForm = true

        for (state in forms.value.values) {
            onEvent(ValidationEvent.TextFieldValueChange(state))
            if (state.isRequired && state.hasError) {
                isValidForm = false
                break
            }
        }

        coroutineScope.launch {
            if (isValidForm) {
                _validationEvent.emit(ValidationResultEvent.Success)
            }
        }

    }
}