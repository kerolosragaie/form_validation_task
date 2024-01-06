package com.kerollosragaie.appvalidation.features.auth.presentation.components

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.kerollosragaie.appvalidation.AsyncTimer
import com.kerollosragaie.appvalidation.core.theme.AppValidationTheme
import com.kerollosragaie.appvalidation.core.utils.validation.Validator
import com.kerollosragaie.appvalidation.features.auth.presentation.component.FormFieldsSemanticsDescription.PASSWORD_FIELD_TAG
import com.kerollosragaie.appvalidation.features.auth.presentation.component.FormFieldsSemanticsDescription.PASSWORD_VISIBLE_ICON_TAG
import com.kerollosragaie.appvalidation.features.auth.presentation.component.PasswordFormField
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class PasswordFormFieldTesting {
    @get:Rule
    val testRule: ComposeContentTestRule = createComposeRule()

    //Form field  name:
    private val passwordHintName = "Password"

    //call-back variables
    private var textCallBack = ""
    private var isValidCallBack: Boolean = false

    private fun setPasswordFormField() {
        isValidCallBack = false

        testRule.setContent {
            AppValidationTheme {
                PasswordFormField(
                    validator = Validator(),
                    onValueChange = { text, isValid ->
                        textCallBack = text
                        isValidCallBack = isValid
                    },
                )
            }
        }
    }

    private fun validatePassword(
        passwordToWrite: String,
        expectedIsValid: Boolean,
        delay: Long = 3000,
    ) {
        setPasswordFormField()
        //Show Password
        testRule.onNodeWithTag(PASSWORD_VISIBLE_ICON_TAG).performClick()
        // Sets the TextField value
        testRule.onNodeWithTag(PASSWORD_FIELD_TAG).performTextInput(passwordToWrite)
        testRule.waitForIdle()
        AsyncTimer.asyncTimer(testRule, delay)
        //Is entered password valid?
        testRule.onNodeWithTag(PASSWORD_FIELD_TAG).assertTextEquals(passwordHintName, textCallBack)
        assertEquals(expectedIsValid, isValidCallBack)
    }

    @Test
    fun passwordNotContain_Letters() {
        validatePassword(passwordToWrite = "1234567@", false)
    }

    @Test
    fun passwordNotContain_Numbers() {
        validatePassword(passwordToWrite = "asdasdad@@", false)
    }

    @Test
    fun passwordNotContain_SpecialCharacters() {
        validatePassword(passwordToWrite = "asdasdasd2223", false)
    }

    @Test
    fun passwordLessThen_8() {
        validatePassword(passwordToWrite = "asda", false)
    }

    @Test
    fun password_IsValid() {
        validatePassword(passwordToWrite = "asdasdas2@", true)
    }
}
