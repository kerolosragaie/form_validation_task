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
        val updatedForm = baseValidation.forms.value.toMutableMap()
        updatedForm[SignInTextFieldId.MOBILE_NUMBER] = mobNumberValidationState
        updatedForm[SignInTextFieldId.PASSWORD] = passwordValidationState
        baseValidation.forms.value = updatedForm
    }

}

enum class SignInTextFieldId : TextFieldId {
    MOBILE_NUMBER,
    PASSWORD,
}