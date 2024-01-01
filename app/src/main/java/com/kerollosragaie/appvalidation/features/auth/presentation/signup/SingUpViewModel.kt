package com.kerollosragaie.appvalidation.features.auth.presentation.signup

import androidx.lifecycle.ViewModel
import com.kerollosragaie.appvalidation.core.components.TextFieldType
import com.kerollosragaie.appvalidation.core.utils.validation.BaseValidation
import com.kerollosragaie.appvalidation.core.utils.validation.interfaces.TextFieldId
import com.kerollosragaie.appvalidation.core.utils.validation.state.ValidationState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SingUpViewModel @Inject constructor(
    val baseValidation: BaseValidation,
) : ViewModel() {
    private val fullNameValidationState = ValidationState(
        id = SignUpTextFieldId.FULL_NAME,
        textFieldType = TextFieldType.Text,
    )

    private val mobileNumberValidationState = ValidationState(
        id = SignUpTextFieldId.MOBILE_NUMBER,
        textFieldType = TextFieldType.Number,
    )

    private val passwordValidationState = ValidationState(
        id = SignUpTextFieldId.PASSWORD,
        textFieldType = TextFieldType.Password,
    )

    var isSubmitted: Boolean = false

    init {
        val updatedForm = baseValidation.forms.value.toMutableMap()
        updatedForm[SignUpTextFieldId.FULL_NAME] = fullNameValidationState
        updatedForm[SignUpTextFieldId.MOBILE_NUMBER] = mobileNumberValidationState
        updatedForm[SignUpTextFieldId.PASSWORD] = passwordValidationState
        baseValidation.forms.value = updatedForm
    }
}

enum class SignUpTextFieldId : TextFieldId {
    FULL_NAME,
    MOBILE_NUMBER,
    PASSWORD,
}


