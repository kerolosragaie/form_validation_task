package com.kerollosragaie.appvalidation.features.auth.presentation.component

import android.util.Patterns
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
fun MobNumberFormField(
    @StringRes hint: Int = R.string.mobile_number,
    onValueChange: (validationResultState: ValidationResultState) -> Unit
) {
    var text by rememberSaveable {
        mutableStateOf("")
    }

    CustomTextField(
        modifier = Modifier.fillMaxWidth(0.85f),
        text = text,
        errorMessageId = validateMobNumberField(text).errorMessageId,
        hint = hint,
        onValueChange = {
            text = it
            onValueChange(validateMobNumberField(text))
        },
        type = TextFieldType.Number,
    )
}

private fun validateMobNumberField(text: String): ValidationResultState {
    val validationResultState: ValidationResultState by lazy { ValidationResultState() }

    return if (text.isBlank()) {
        validationResultState.copy(
            isValid = false,
            errorMessageId = R.string.the_field_can_not_be_blank,
        )
    } else if (text.length != 11) {
        validationResultState.copy(
            isValid = false,
            errorMessageId = R.string.must_be_11_digits
        )
    } else if (!Patterns.PHONE.matcher(text).matches()) {
        validationResultState.copy(
            isValid = false,
            errorMessageId = R.string.that_is_not_a_valid_number
        )
    } else {
        validationResultState.copy(isValid = true)
    }
}