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
import com.kerollosragaie.appvalidation.core.theme.AppValidationTheme
import com.kerollosragaie.appvalidation.features.auth.presentation.component.FullNameFormField
import com.kerollosragaie.appvalidation.features.auth.presentation.component.MobNumberFormField
import com.kerollosragaie.appvalidation.features.auth.presentation.component.PasswordFormField

@Composable
fun SignUpScreen(
    viewModel: SingUpViewModel = hiltViewModel(),
    navigateToSignIn: () -> Unit,
) {
    val context = LocalContext.current
    var isValidName by remember {
        mutableStateOf(false)
    }
    var isValidMobile by remember {
        mutableStateOf(false)
    }
    var isValidPassword by remember {
        mutableStateOf(false)
    }
    val isValidForm: Boolean = isValidName && isValidMobile && isValidPassword


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

        FullNameFormField(
            onValueChange = { validationResultState ->
                isValidName = validationResultState.isValid
            },
        )

        Spacer(modifier = Modifier.height(20.dp))

        MobNumberFormField(
            onValueChange = { validationResultState ->
                isValidMobile = validationResultState.isValid
            },
        )

        Spacer(modifier = Modifier.height(20.dp))

        PasswordFormField(
            onValueChange = { validationResultState ->
                isValidPassword = validationResultState.isValid
            },
        )

        Spacer(modifier = Modifier.height(40.dp))

        CustomButton(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(50.dp),
            enabled = isValidForm,
            onClick = {
                Toast.makeText(context, R.string.sign_up_success, Toast.LENGTH_SHORT).show()
            },
            textId = R.string.sign_up,
            cornerRadius = 25.dp,
        )
    }
}


@Preview(showSystemUi = true)
@Composable
fun PrevSignUp() {
    val viewModel = SingUpViewModel()
    AppValidationTheme {
        SignUpScreen(viewModel = viewModel) {

        }
    }
}

