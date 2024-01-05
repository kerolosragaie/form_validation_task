package com.kerollosragaie.appvalidation.features.auth.presentation.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kerollosragaie.appvalidation.R
import com.kerollosragaie.appvalidation.core.theme.AppValidationTheme
import com.kerollosragaie.appvalidation.core.utils.validation.Validator

@Composable
fun MobNumberFormField(
    modifier: Modifier = Modifier,
    @StringRes hint: Int = R.string.mobile_number,
    onValueChange: (text: String, isValid: Boolean) -> Unit
) {
    var text by remember {
        mutableStateOf("")
    }

    val mobileValidationString: Int? = Validator.handleMobileValidation(text)

    //! onValueChange should be here, to callback the updated value
    //! otherwise use view-model to pass data between composable func.
    //! and view-model

    Column(
        modifier = modifier,
    ) {
        TextField(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(2.dp),
                )
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next,
            ),
            value = text,
            textStyle = MaterialTheme.typography.bodyMedium,
            onValueChange = {
                text = it
                onValueChange(it, mobileValidationString == null)
            },
            colors = TextFieldDefaults.colors(
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            label = {
                Text(
                    text = stringResource(id = hint),
                    style = MaterialTheme.typography.bodySmall,
                    softWrap = true,
                )
            },
        )

        ValidationText(
            text = mobileValidationString,
        )

    }
}


@Composable
private fun ValidationText(@StringRes text: Int?) {

    val color = if (text == null) Color.Green else Color.Red

    text?.let {
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = stringResource(id = it),
            color = color,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PrevMobileFormField() {
    AppValidationTheme {
        MobNumberFormField(
            onValueChange = { _, _ -> }
        )
    }
}
