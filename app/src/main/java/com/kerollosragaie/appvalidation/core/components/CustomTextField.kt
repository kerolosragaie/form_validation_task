package com.kerollosragaie.appvalidation.core.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kerollosragaie.appvalidation.R

enum class TextFieldType {
    Number,
    Password,
    Text,
}

@Composable
fun CustomTextField(
    modifier: Modifier,
    text: String,
    @StringRes errorMessageId: Int? = null,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    @StringRes hint: Int,
    hintTextStyle: TextStyle = MaterialTheme.typography.bodySmall,
    onValueChange: (String) -> Unit,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    cornerRadius: Dp = 0.dp,
    type: TextFieldType,
) {
    var passwordVisible by remember { mutableStateOf(true) }

    var trailingIconId = when (type) {
        TextFieldType.Password -> R.drawable.ic_visibility_on
        else -> null
    }

    if (trailingIconId != null) {
        trailingIconId =
            if (passwordVisible) R.drawable.ic_visibility_off else R.drawable.ic_visibility_on
    }

    Column {

        TextField(
            modifier = modifier
                .background(color = color, shape = RoundedCornerShape(cornerRadius))
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                keyboardType = when (type) {
                    TextFieldType.Number -> KeyboardType.Number
                    TextFieldType.Password -> KeyboardType.Password
                    TextFieldType.Text -> KeyboardType.Text
                },
                imeAction = ImeAction.Next,
            ),
            value = text,
            textStyle = textStyle,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.colors(
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            label = {
                Text(text = stringResource(id = hint), style = hintTextStyle, softWrap = true)
            },

            visualTransformation =
            if (type == TextFieldType.Password) {
                if (passwordVisible) PasswordVisualTransformation() else VisualTransformation.None
            } else VisualTransformation.None,
            trailingIcon = {

                if (trailingIconId != null) {
                    Icon(
                        modifier = Modifier.clickable {
                            passwordVisible = !passwordVisible
                        },
                        painter = painterResource(id = trailingIconId),
                        contentDescription = "password",
                    )
                }
            }
        )

        errorMessageId?.let {
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                text = stringResource(id = it),
                color = MaterialTheme.colorScheme.error,
            )
        }

    }
}


@Preview
@Composable
fun PrevCustomTextField() {
    CustomTextField(
        modifier = Modifier.fillMaxWidth(0.85f),
        text = "Password",
        hint = R.string.password,
        onValueChange = {},
        cornerRadius = 15.dp,
        type = TextFieldType.Password,
    )
}