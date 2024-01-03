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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.kerollosragaie.appvalidation.core.components.TextFieldType
import com.kerollosragaie.appvalidation.core.utils.validation.Validator
import com.kerollosragaie.appvalidation.features.auth.presentation.component.TextFormField

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navigateToSignUp: () -> Unit,
) {
    val context = LocalContext.current

    val validator = viewModel.validator
    var isValidMobile by remember {
        mutableStateOf(false)
    }
    var isValidPassword by remember {
        mutableStateOf(false)
    }
    val isValidForm: Boolean = isValidMobile && isValidPassword

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

        TextFormField(
            hint = R.string.mobile_number,
            textFieldType = TextFieldType.Number,
            validateResult = { text -> validator.validateTextField(text, TextFieldType.Number) },
            onValueChange = { isValid -> isValidMobile = isValid }
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextFormField(
            hint = R.string.password,
            textFieldType = TextFieldType.Password,
            validateResult = { text -> validator.validateTextField(text, TextFieldType.Password) },
            onValueChange = { isValid -> isValidPassword = isValid }
        )

        Spacer(modifier = Modifier.height(40.dp))

        CustomButton(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(50.dp),
            enabled = isValidForm,
            onClick = {
                Toast.makeText(context, R.string.sign_in_success, Toast.LENGTH_SHORT).show()
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


@Preview(showSystemUi = true)
@Composable
fun PrevSignInScreen() {
    val viewModel = SignInViewModel(validator = Validator())
    AppValidationTheme {
        SignInScreen(viewModel = viewModel) {

        }
    }
}