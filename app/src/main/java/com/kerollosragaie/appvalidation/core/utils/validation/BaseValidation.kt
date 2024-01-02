package com.kerollosragaie.appvalidation.core.utils.validation

import com.kerollosragaie.appvalidation.core.components.TextFieldType
import com.kerollosragaie.appvalidation.core.utils.validation.state.ValidationResultState
import com.kerollosragaie.appvalidation.core.utils.validation.usecase.ValidateNumber
import com.kerollosragaie.appvalidation.core.utils.validation.usecase.ValidatePassword
import com.kerollosragaie.appvalidation.core.utils.validation.usecase.ValidateText

class BaseValidation {
    fun validateTextField(
        text:String,
        textFormFieldType: TextFieldType=TextFieldType.Text,
    ):ValidationResultState{
        val validatorType = when(textFormFieldType){
            TextFieldType.Number -> ValidateNumber()
            TextFieldType.Password -> ValidatePassword()
            TextFieldType.Text -> ValidateText()
        }
        return validatorType.execute(text.trim())
    }
}