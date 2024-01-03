package com.kerollosragaie.appvalidation.features.auth.presentation.signin

import androidx.lifecycle.ViewModel
import com.kerollosragaie.appvalidation.core.utils.validation.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    val validator: Validator,
) : ViewModel()
