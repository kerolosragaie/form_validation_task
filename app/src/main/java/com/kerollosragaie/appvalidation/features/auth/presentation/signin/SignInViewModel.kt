package com.kerollosragaie.appvalidation.features.auth.presentation.signin

import androidx.lifecycle.ViewModel
import com.kerollosragaie.appvalidation.core.components.TextFieldType
import com.kerollosragaie.appvalidation.core.utils.validation.BaseValidation
import com.kerollosragaie.appvalidation.core.utils.validation.interfaces.TextFieldId
import com.kerollosragaie.appvalidation.core.utils.validation.state.ValidationState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    val baseValidation: BaseValidation,
) : ViewModel() {
    private var mobNumberValidationState = ValidationState(
        id = SignInTextFieldId.MOBILE_NUMBER,
        textFieldType = TextFieldType.Number,
    )
    private var passwordValidationState = ValidationState(
        id = SignInTextFieldId.PASSWORD,
        textFieldType = TextFieldType.Password,
    )

    var isSubmitted: Boolean = false


    init {
        baseValidation.forms[SignInTextFieldId.MOBILE_NUMBER] = mobNumberValidationState
        baseValidation.forms[SignInTextFieldId.PASSWORD] = passwordValidationState
    }

}

enum class SignInTextFieldId : TextFieldId {
    MOBILE_NUMBER,
    PASSWORD,
}