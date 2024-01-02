package com.kerollosragaie.appvalidation.features.auth.presentation.signup

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.kerollosragaie.appvalidation.core.utils.validation.BaseValidation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SingUpViewModel @Inject constructor(
    val baseValidation: BaseValidation,
) : ViewModel() {
    val fullNameText = mutableStateOf("")
    val mobNumberText = mutableStateOf("")
    val passwordText = mutableStateOf("")
}
