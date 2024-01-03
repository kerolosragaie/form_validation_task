package com.kerollosragaie.appvalidation.core.utils.validation

import com.kerollosragaie.appvalidation.core.components.TextFieldType
import com.kerollosragaie.appvalidation.core.utils.validation.state.ValidationResultState
import com.kerollosragaie.appvalidation.core.utils.validation.usecase.ValidateNumber
import com.kerollosragaie.appvalidation.core.utils.validation.usecase.ValidatePassword
import com.kerollosragaie.appvalidation.core.utils.validation.usecase.ValidateText

class Validator {
    fun validateTextField(
        text: String,
        textFormFieldType: TextFieldType = TextFieldType.Text,
    ): ValidationResultState = when (textFormFieldType) {
        TextFieldType.Number -> ValidateNumber()
        TextFieldType.Password -> ValidatePassword()
        TextFieldType.Text -> ValidateText()
    }.run {
        execute(text.trim())
    }
}