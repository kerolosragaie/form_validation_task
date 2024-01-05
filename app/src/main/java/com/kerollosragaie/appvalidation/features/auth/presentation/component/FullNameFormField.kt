package com.kerollosragaie.appvalidation.features.auth.presentation.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kerollosragaie.appvalidation.R
import com.kerollosragaie.appvalidation.core.components.CustomTextField
import com.kerollosragaie.appvalidation.core.components.TextFieldType
import com.kerollosragaie.appvalidation.core.theme.AppValidationTheme
import com.kerollosragaie.appvalidation.features.auth.presentation.state.ValidationResultState

@Composable
fun FullNameFormField(
    @StringRes hint: Int = R.string.full_name,
    onValueChange: (validationResultState: ValidationResultState) -> Unit
) {

    var text by rememberSaveable {
        mutableStateOf("")
    }

    //! will not work as recomposition happens but validator will work with old value
    //! Q) why will work with old value?
    //! A) bec. validateNameField() function works with text var in same level
    //! i.e: both validateNameField() function and text var in same composable function
    //! so to make validateNameField() function work should pass data from/to view-model
    val validator = validateNameField(text)

    CustomTextField(
        modifier = Modifier.fillMaxWidth(0.85f),
        text = text,
        errorMessageId = validator.errorMessageId,
        hint = hint,
        onValueChange = {
            text = it
            onValueChange(validator)
        },
        type = TextFieldType.Text,
    )
}

private fun validateNameField(text: String): ValidationResultState {
    val validationResultState: ValidationResultState by lazy { ValidationResultState() }

    return if (text.isEmpty()) {
        validationResultState.copy(
            isValid = false,
            errorMessageId = R.string.the_field_can_not_be_blank,
        )
    } else {
        validationResultState.copy(isValid = true)
    }
}

@Preview(showBackground = true)
@Composable
fun PrevFullNameFormField() {
    AppValidationTheme {
        FullNameFormField(
            onValueChange = { _ ->
            },
        )
    }
}
