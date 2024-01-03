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
import java.util.regex.Pattern

@Composable
fun PasswordFormField(
    @StringRes hint: Int = R.string.password,
    onValueChange: (validationResultState: ValidationResultState) -> Unit
) {
    var text by rememberSaveable {
        mutableStateOf("")
    }

    CustomTextField(
        modifier = Modifier.fillMaxWidth(0.85f),
        text = text,
        errorMessageId = validatePasswordField(text).errorMessageId,
        hint = hint,
        onValueChange = {
            text = it
            onValueChange(validatePasswordField(text))
        },
        type = TextFieldType.Password,
    )
}

private fun validatePasswordField(text: String): ValidationResultState {
    val validationResultState: ValidationResultState by lazy { ValidationResultState() }

    val isContainLettersAndDigits = text.any { it.isDigit() } && text.any { it.isLetter() }
    val specialChar: Pattern = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]")

    return if (text.isBlank()) {
        validationResultState.copy(
            isValid = false,
            errorMessageId = R.string.the_field_can_not_be_blank
        )
    } else if (text.length < 8) {
        validationResultState.copy(
            isValid = false,
            errorMessageId = R.string.the_password_needs_to_consist_of_at_least_8_characters
        )
    } else if (!specialChar.matcher(text).find()) {
        validationResultState.copy(
            isValid = false,
            errorMessageId = R.string.the_password_should_contain_at_least_special_character
        )
    } else if (!isContainLettersAndDigits) {
        validationResultState.copy(
            isValid = false,
            errorMessageId = R.string.the_password_needs_to_contain_at_least_one_letter_and_digit
        )
    } else {
        validationResultState.copy(isValid = true)
    }
}