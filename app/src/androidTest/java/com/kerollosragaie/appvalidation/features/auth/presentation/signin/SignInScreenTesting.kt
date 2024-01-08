package com.kerollosragaie.appvalidation.features.auth.presentation.signin

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.kerollosragaie.appvalidation.AsyncTimer
import com.kerollosragaie.appvalidation.core.theme.AppValidationTheme
import com.kerollosragaie.appvalidation.core.utils.validation.Validator
import com.kerollosragaie.appvalidation.features.auth.presentation.component.FormFieldsSemanticsDescription.MOB_NUMBER_FIELD_TAG
import com.kerollosragaie.appvalidation.features.auth.presentation.component.FormFieldsSemanticsDescription.PASSWORD_FIELD_TAG
import com.kerollosragaie.appvalidation.features.auth.presentation.component.FormFieldsSemanticsDescription.PASSWORD_VISIBLE_ICON_TAG
import com.kerollosragaie.appvalidation.features.auth.presentation.component.FormFieldsSemanticsDescription.SUBMIT_FORM_BUTTON
import org.junit.Rule
import org.junit.Test

class SignInScreenTesting {
    @get:Rule
    val testRule: ComposeContentTestRule = createComposeRule()

    private fun setSignInScreen() {
        val viewModel = SignInViewModel(validator = Validator())

        testRule.setContent {
            AppValidationTheme {
                SignInScreen(viewModel = viewModel) {

                }
            }
        }
    }

    private fun signInScreen_IsSubmitEnabled(
        mobNumber: String,
        password: String,
        assertSubmitEnabled: Boolean,
        delay: Long = 3000,
    ) {
        setSignInScreen()
        //Show Password
        testRule.onNodeWithTag(PASSWORD_VISIBLE_ICON_TAG).performClick()
        // Sets the TextField value
        testRule.onNodeWithTag(MOB_NUMBER_FIELD_TAG).performTextInput(mobNumber)
        testRule.onNodeWithTag(PASSWORD_FIELD_TAG).performTextInput(password)
        testRule.waitForIdle()
        AsyncTimer.asyncTimer(testRule, delay)
        //Is submit button enabled?
        if (assertSubmitEnabled)
            testRule.onNodeWithTag(SUBMIT_FORM_BUTTON).assertIsEnabled()
        else
            testRule.onNodeWithTag(SUBMIT_FORM_BUTTON).assertIsNotEnabled()
        //Perform click on submit button
        testRule.onNodeWithTag(SUBMIT_FORM_BUTTON).performClick()
    }

    @Test
    fun submitButton_IsEnabled() {
        signInScreen_IsSubmitEnabled(
            "11111111111",
            "asdasdas2@",
            assertSubmitEnabled = true,
            delay = 4000,
        )
    }

    @Test
    fun submitButton_IsNotEnabled() {
        signInScreen_IsSubmitEnabled(
            "1111111111",
            "asdasdas2@",
            assertSubmitEnabled = false,
            delay = 4000,
        )
    }

}