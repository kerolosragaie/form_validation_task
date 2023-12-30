package com.kerollosragaie.appvalidation.features.auth.presentation.signup


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kerollosragaie.appvalidation.R
import com.kerollosragaie.appvalidation.core.components.CustomButton
import com.kerollosragaie.appvalidation.core.components.CustomTextField
import com.kerollosragaie.appvalidation.core.components.TextFieldType
import com.kerollosragaie.appvalidation.core.theme.AppValidationTheme
import com.kerollosragaie.appvalidation.core.utils.validation.BaseValidation
import com.kerollosragaie.appvalidation.core.utils.validation.event.ValidationEvent
import com.kerollosragaie.appvalidation.core.utils.validation.event.ValidationResultEvent


@Composable
fun SignUpScreen(
    viewModel: SingUpViewModel = hiltViewModel(),
    navigateToSignIn: () -> Unit,
) {
    val context = LocalContext.current
    val baseValidation = viewModel.baseValidation

    LaunchedEffect(context) {
        baseValidation.validationEvent.collect { resultEvent ->
            if (resultEvent == ValidationResultEvent.Success && !viewModel.isSubmitted) {
                Toast.makeText(context, R.string.sign_up_success, Toast.LENGTH_LONG)
                    .show()
                viewModel.isSubmitted = true
            }
        }
    }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        //Back bttn
        IconButton(
            modifier = Modifier
                .align(Alignment.Start),
            onClick = { navigateToSignIn.invoke() },
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground),
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.create_account).uppercase(),
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(40.dp))

        CustomTextField(
            modifier = Modifier.fillMaxWidth(0.85f),
            text = baseValidation.forms[SignUpTextFieldId.FULL_NAME]?.text.toString(),
            errorMessageId = baseValidation.forms[SignUpTextFieldId.FULL_NAME]?.errorMessageId,
            hint = R.string.full_name,
            onValueChange = {
                val fullNameField = baseValidation.forms[SignUpTextFieldId.FULL_NAME]!!
                baseValidation.onEvent(
                    ValidationEvent.TextFieldValueChange(
                        fullNameField.copy(text = it)
                    )
                )
            },
            type = TextFieldType.Text,
        )

        Spacer(modifier = Modifier.height(20.dp))

        CustomTextField(
            modifier = Modifier.fillMaxWidth(0.85f),
            text = baseValidation.forms[SignUpTextFieldId.MOBILE_NUMBER]?.text.toString(),
            errorMessageId = baseValidation.forms[SignUpTextFieldId.MOBILE_NUMBER]?.errorMessageId,
            hint = R.string.mobile_number,
            onValueChange = {
                val mobileNumberField = baseValidation.forms[SignUpTextFieldId.MOBILE_NUMBER]!!
                baseValidation.onEvent(
                    ValidationEvent.TextFieldValueChange(
                        mobileNumberField.copy(text = it)
                    )
                )
            },
            type = TextFieldType.Number,
        )

        Spacer(modifier = Modifier.height(20.dp))

        CustomTextField(
            modifier = Modifier.fillMaxWidth(0.85f),
            text = baseValidation.forms[SignUpTextFieldId.PASSWORD]?.text.toString(),
            errorMessageId = baseValidation.forms[SignUpTextFieldId.PASSWORD]?.errorMessageId,
            hint = R.string.password,
            onValueChange = {
                val passwordField = baseValidation.forms[SignUpTextFieldId.PASSWORD]!!
                baseValidation.onEvent(
                    ValidationEvent.TextFieldValueChange(
                        passwordField.copy(text = it)
                    )
                )
            },
            type = TextFieldType.Password,
        )

        Spacer(modifier = Modifier.height(40.dp))

        CustomButton(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(50.dp),
            enabled = baseValidation.forms.all { !it.value.hasError },
            onClick = {
                baseValidation.onEvent(ValidationEvent.Submit)
            },
            textId = R.string.sign_up,
            cornerRadius = 25.dp,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PrevSignUp() {
    val viewModel = SingUpViewModel(baseValidation = BaseValidation())
    AppValidationTheme {
        SignUpScreen(viewModel = viewModel) {

        }
    }
}

