package com.kerollosragaie.appvalidation.features.auth.presentation.signin

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.kerollosragaie.appvalidation.core.utils.validation.BaseValidation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    val baseValidation: BaseValidation,
) : ViewModel() {
   val mobNumberText = mutableStateOf("")
   val passwordText = mutableStateOf("")

}
