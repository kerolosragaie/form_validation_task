package com.kerollosragaie.appvalidation.features.auth.presentation.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.kerollosragaie.appvalidation.R
import com.kerollosragaie.appvalidation.core.components.CustomTextField
import com.kerollosragaie.appvalidation.core.components.TextFieldType
import com.kerollosragaie.appvalidation.core.utils.validation.state.ValidationResultState

@Composable
fun TextFormField(
    @StringRes hint: Int,
    textFieldType: TextFieldType=TextFieldType.Text,
    validateResult: (text: String) -> ValidationResultState,
    onValueChange: (isValid: Boolean) -> Unit
) {
    var text by rememberSaveable {
        mutableStateOf("")
    }

    CustomTextField(
        modifier = Modifier.fillMaxWidth(0.85f),
        text = text,
        errorMessageId = validateResult(text).errorMessageId,
        hint = hint,
        onValueChange = {
            text = it
            onValueChange(validateResult(text).isValid)
        },
        type = textFieldType,
    )

}