package com.kerollosragaie.appvalidation.core.utils.validation

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kerollosragaie.appvalidation.core.components.TextFieldType
import com.kerollosragaie.appvalidation.core.utils.validation.event.ValidationEvent
import com.kerollosragaie.appvalidation.core.utils.validation.event.ValidationResultEvent
import com.kerollosragaie.appvalidation.core.utils.validation.interfaces.TextFieldId
import com.kerollosragaie.appvalidation.core.utils.validation.state.ValidationState
import com.kerollosragaie.appvalidation.core.utils.validation.usecase.ValidateNumber
import com.kerollosragaie.appvalidation.core.utils.validation.usecase.ValidatePassword
import com.kerollosragaie.appvalidation.core.utils.validation.usecase.ValidateText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class BaseValidationViewModel @Inject constructor() : ViewModel() {
    val forms = mutableStateMapOf<TextFieldId, ValidationState>()

    private val _validationEvent: MutableSharedFlow<ValidationResultEvent?> = MutableSharedFlow()
    val validationEvent : SharedFlow<ValidationResultEvent?> = _validationEvent

    fun onEvent(event: ValidationEvent) {
        when (event) {
            is ValidationEvent.TextFieldValueChange -> {
                val validatorType = when (event.validationState.textFieldType) {
                    TextFieldType.Number -> ValidateNumber()
                    TextFieldType.Password -> ValidatePassword()
                    TextFieldType.Text -> ValidateText()
                }
                val validationResult = validatorType.execute(event.validationState.text.trim())

                forms[event.validationState.id] = if (validationResult.isValid) {
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
            }

            is ValidationEvent.Submit -> isValidForm()
        }
    }

    private fun isValidForm() {
        var isValidForm = true

        for (state in forms.values) {
            onEvent(ValidationEvent.TextFieldValueChange(state))
            if (state.isRequired && state.hasError) {
                isValidForm = false
            }
        }

        viewModelScope.launch {
            if (isValidForm) {
                _validationEvent.emit(ValidationResultEvent.Success)
            }
        }

    }

}