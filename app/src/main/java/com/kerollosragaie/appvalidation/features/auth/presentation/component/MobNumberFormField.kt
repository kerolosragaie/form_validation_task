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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kerollosragaie.appvalidation.R
import com.kerollosragaie.appvalidation.core.theme.AppValidationTheme
import com.kerollosragaie.appvalidation.core.utils.validation.Validator

@Composable
fun MobNumberFormField(
    modifier: Modifier = Modifier,
    validator: Validator,
    @StringRes hint: Int = R.string.mobile_number,
    onValueChange: (text: String, isValid: Boolean) -> Unit
) {
    var text by remember {
        mutableStateOf("")
    }

    val mobileValidationString: Int? = validator.handleMobileValidation(text)

    //! onValueChange should be here, to callback the updated value
    //! otherwise use view-model to pass data between composable func.
    //! and view-model, (we pass states between Composables not view-model)
    onValueChange(text, mobileValidationString == null)

    Column(
        modifier = modifier,
    ) {
        TextField(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(2.dp),
                )
                .fillMaxWidth()
                .testTag(FormFieldsSemanticsDescription.MOB_NUMBER_FIELD_TAG),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next,
            ),
            value = text,
            textStyle = MaterialTheme.typography.bodyMedium,
            onValueChange = {
                text = it
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
    text?.let {
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .testTag(FormFieldsSemanticsDescription.ERROR_MESSAGE_ID),
            text = stringResource(id = text),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.error,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PrevMobileFormField() {
    AppValidationTheme {
        MobNumberFormField(
            validator = Validator(),
            onValueChange = { _, _ -> }
        )
    }
}
