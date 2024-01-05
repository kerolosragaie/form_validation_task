package com.kerollosragaie.appvalidation.features.auth.presentation.component

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kerollosragaie.appvalidation.R
import com.kerollosragaie.appvalidation.core.theme.AppValidationTheme
import com.kerollosragaie.appvalidation.core.utils.validation.enums.PasswordValidationType
import com.kerollosragaie.appvalidation.core.utils.validation.Validator


@Composable
fun PasswordFormField(
    modifier: Modifier = Modifier,
    validator: Validator,
    @StringRes hint: Int = R.string.password,
    onValueChange: (text: String, isValid: Boolean) -> Unit
) {
    var text by rememberSaveable {
        mutableStateOf("")
    }

    var passwordVisible by rememberSaveable { mutableStateOf(true) }

    val trailingIconId =
        if (passwordVisible) R.drawable.ic_visibility_off else R.drawable.ic_visibility_on

    var isPasswordValid by rememberSaveable {
        mutableStateOf(false)
    }
    //! 1- user type letter => a
    //! 2- recomposition happens
    //! 3- text value is updated and isPasswordValid value
    onValueChange(text, isPasswordValid)


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

            visualTransformation = if (passwordVisible)
                PasswordVisualTransformation()
            else
                VisualTransformation.None,

            trailingIcon = {
                Icon(
                    modifier = Modifier.clickable {
                        passwordVisible = !passwordVisible
                    },
                    painter = painterResource(id = trailingIconId),
                    contentDescription = "password",
                )
            }
        )

        PasswordValidationType.entries.forEach { passwordValidationType ->
            isPasswordValid = validator.handlePasswordValidation(text, passwordValidationType)

            Spacer(modifier = Modifier.height(2.dp))
            ValidationText(
                text = passwordValidationType.stringResource,
                isValid = isPasswordValid
            )
        }

    }
}

@Composable
private fun ValidationText(@StringRes text: Int, isValid: Boolean) {
    val iconResource = if (isValid) R.drawable.ic_check else R.drawable.ic_close
    val color = when (isValid) {
        true -> Color.Green
        false -> Color.Red
    }

    Row {
        AnimatedContent(targetState = iconResource, label = "") { icon ->
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = color,
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = stringResource(id = text),
            color = color,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PrevPasswordFormField() {
    AppValidationTheme {
        PasswordFormField(
            validator = Validator(),
            onValueChange = { _, _ -> }
        )
    }
}
