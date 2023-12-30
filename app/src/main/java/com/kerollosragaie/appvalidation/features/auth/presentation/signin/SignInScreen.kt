package com.kerollosragaie.appvalidation.features.auth.presentation.signin

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kerollosragaie.appvalidation.R
import com.kerollosragaie.appvalidation.core.components.CustomButton
import com.kerollosragaie.appvalidation.core.theme.AppValidationTheme
import com.kerollosragaie.appvalidation.core.components.CustomTextField
import com.kerollosragaie.appvalidation.core.components.TextFieldType
import com.kerollosragaie.appvalidation.core.utils.validation.event.ValidationEvent
import com.kerollosragaie.appvalidation.core.utils.validation.event.ValidationResultEvent

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navigateToSignUp: () -> Unit,
) {
    val context = LocalContext.current

    LaunchedEffect(context) {
        viewModel.validationEvent
            .collect { resultEvent ->
                if (resultEvent == ValidationResultEvent.Success && !viewModel.isSubmitted) {
                    Toast.makeText(context, R.string.sign_in_success, Toast.LENGTH_LONG)
                        .show()
                    viewModel.isSubmitted = true
                }
            }
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.width(250.dp),
            painter = painterResource(id = R.drawable.login),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.login).uppercase(),
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.please_sign_in_to_continue),
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.height(10.dp))

        CustomTextField(
            modifier = Modifier.fillMaxWidth(0.85f),
            text = viewModel.forms[SignInTextFieldId.MOBILE_NUMBER]?.text.toString(),
            errorMessageId = viewModel.forms[SignInTextFieldId.MOBILE_NUMBER]?.errorMessageId,
            hint = R.string.mobile_number,
            onValueChange = {
                val mobileNumberField = viewModel.forms[SignInTextFieldId.MOBILE_NUMBER]!!
                viewModel.onEvent(
                    ValidationEvent.TextFieldValueChange(
                        mobileNumberField.copy(text = it)
                    )
                )
            },
            cornerRadius = 15.dp,
            type = TextFieldType.Number,
        )

        Spacer(modifier = Modifier.height(20.dp))

        CustomTextField(
            modifier = Modifier.fillMaxWidth(0.85f),
            text = viewModel.forms[SignInTextFieldId.PASSWORD]?.text.toString(),
            errorMessageId = viewModel.forms[SignInTextFieldId.PASSWORD]?.errorMessageId,
            hint = R.string.password,
            onValueChange = {
                val passwordField = viewModel.forms[SignInTextFieldId.PASSWORD]!!
                viewModel.onEvent(
                    ValidationEvent.TextFieldValueChange(
                        passwordField.copy(text = it)
                    )
                )
            },
            cornerRadius = 15.dp,
            type = TextFieldType.Password,
        )

        Spacer(modifier = Modifier.height(40.dp))

        CustomButton(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(50.dp),
            enabled = viewModel.forms.all { !it.value.hasError },
            onClick = {
                viewModel.onEvent(ValidationEvent.Submit)
            },
            textId = R.string.login,
            cornerRadius = 25.dp,
        )

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier.clickable { navigateToSignUp.invoke() }
        ) {
            Text(
                text = stringResource(id = R.string.do_not_have_an_account)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = stringResource(id = R.string.sign_up)
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun PrevSignInScreen() {
    AppValidationTheme {
        SignInScreen {}
    }
}