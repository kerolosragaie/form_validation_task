package com.kerollosragaie.appvalidation.core.navigation

import com.kerollosragaie.appvalidation.core.utils.Constants.SIGN_IN
import com.kerollosragaie.appvalidation.core.utils.Constants.SIGN_UP

sealed class Screen(val route:String){
    object SignInScreen : Screen(SIGN_IN)
    object SignUpScreen : Screen(SIGN_UP)
}