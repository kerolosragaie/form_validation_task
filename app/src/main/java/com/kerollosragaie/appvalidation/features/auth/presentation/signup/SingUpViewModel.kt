package com.kerollosragaie.appvalidation.features.auth.presentation.signup

import com.kerollosragaie.appvalidation.core.components.TextFieldType
import com.kerollosragaie.appvalidation.core.utils.validation.BaseValidationViewModel
import com.kerollosragaie.appvalidation.core.utils.validation.interfaces.TextFieldId
import com.kerollosragaie.appvalidation.core.utils.validation.state.ValidationState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SingUpViewModel @Inject constructor() : BaseValidationViewModel() {
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

    init {
        forms[SignUpTextFieldId.FULL_NAME] = fullNameValidationState
        forms[SignUpTextFieldId.MOBILE_NUMBER] = mobileNumberValidationState
        forms[SignUpTextFieldId.PASSWORD] = passwordValidationState
    }
}

enum class SignUpTextFieldId : TextFieldId {
    FULL_NAME,
    MOBILE_NUMBER,
    PASSWORD,
}


