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
import com.kerollosragaie.appvalidation.core.utils.validation.Validator

@Composable
fun FullNameFormField(
    @StringRes hint: Int = R.string.full_name,
    validator: Validator,
    onValueChange: (text: String, isValid: Boolean) -> Unit
) {

    var text by rememberSaveable {
        mutableStateOf("")
    }

    //! will not work as recomposition happens but validator will work with old value
    //! Q) why will work with old value?
    //! A) bec. handleNameValidation() function works with text var in same level
    //! i.e: both handleNameValidation() function and text var in same composable function
    //! so to make handleNameValidation() function work should pass data from/to view-model
    //! but as state (using data classes)
    //! And if don't want to use view-model so add the onValueChange func. callback
    //! Before Composable func. to call back values probably
    val nameValidationString: Int? = validator.handleNameValidation(text)

    onValueChange(text, nameValidationString == null)

    CustomTextField(
        modifier = Modifier.fillMaxWidth(0.85f),
        text = text,
        errorMessageId = nameValidationString,
        hint = hint,
        onValueChange = {
            text = it
        },
        type = TextFieldType.Text,
    )
}

@Preview(showSystemUi = true)
@Composable
fun PrevFullNameFormField() {
    AppValidationTheme {
        FullNameFormField(
            validator = Validator(),
            onValueChange = { _, _ ->
            },
        )
    }
}
