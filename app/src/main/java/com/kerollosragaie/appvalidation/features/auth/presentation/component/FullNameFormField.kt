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
import com.kerollosragaie.appvalidation.features.auth.presentation.state.ValidationResultState

@Composable
fun FullNameFormField(
    @StringRes hint: Int = R.string.full_name,
    onValueChange: (validationResultState: ValidationResultState) -> Unit
) {
    var text by rememberSaveable {
        mutableStateOf("")
    }

    CustomTextField(
        modifier = Modifier.fillMaxWidth(0.85f),
        text = text,
        errorMessageId = validateNameField(text).errorMessageId,
        hint = hint,
        onValueChange = {
            text = it
            onValueChange(validateNameField(text))
        },
        type = TextFieldType.Text,
    )
}

private fun validateNameField(text: String): ValidationResultState {
    val validationResultState: ValidationResultState by lazy { ValidationResultState() }

    return if (text.isBlank()) {
        validationResultState.copy(
            isValid = false,
            errorMessageId = R.string.the_field_can_not_be_blank,
        )
    } else {
        validationResultState.copy(isValid = true)
    }
}