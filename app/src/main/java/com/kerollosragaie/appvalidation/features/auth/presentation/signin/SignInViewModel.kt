package com.kerollosragaie.appvalidation.features.auth.presentation.signin

import com.kerollosragaie.appvalidation.core.components.TextFieldType
import com.kerollosragaie.appvalidation.core.utils.validation.BaseValidationViewModel
import com.kerollosragaie.appvalidation.core.utils.validation.interfaces.TextFieldId
import com.kerollosragaie.appvalidation.core.utils.validation.state.ValidationState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : BaseValidationViewModel() {
    private var mobNumberValidationState = ValidationState(
        id = SignInTextFieldId.MOBILE_NUMBER,
        textFieldType = TextFieldType.Number,
    )
    private var passwordValidationState = ValidationState(
        id = SignInTextFieldId.PASSWORD,
        textFieldType = TextFieldType.Password,
    )

    init {
        forms[SignInTextFieldId.MOBILE_NUMBER] = mobNumberValidationState
        forms[SignInTextFieldId.PASSWORD] = passwordValidationState
    }

}

enum class SignInTextFieldId : TextFieldId {
    MOBILE_NUMBER,
    PASSWORD,
}