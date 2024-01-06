package com.kerollosragaie.appvalidation.features.auth.presentation.components

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import com.kerollosragaie.appvalidation.AsyncTimer
import com.kerollosragaie.appvalidation.core.theme.AppValidationTheme
import com.kerollosragaie.appvalidation.core.utils.validation.Validator
import com.kerollosragaie.appvalidation.features.auth.presentation.component.FormFieldsSemanticsDescription.ERROR_MESSAGE_ID
import com.kerollosragaie.appvalidation.features.auth.presentation.component.FormFieldsSemanticsDescription.FULL_NAME_FIELD_TAG
import com.kerollosragaie.appvalidation.features.auth.presentation.component.FullNameFormField
import org.junit.Rule
import org.junit.Test

class FullNameFormFieldTesting {
    @get:Rule
    val testRule: ComposeContentTestRule = createComposeRule()

    //Text field hint
    private val textFieldHintName = "Mobile number"

    //call-back variables
    private var textCallBack = ""
    private var isValidCallBack: Boolean = false

    private fun setFullNameFormField() {
        isValidCallBack = false
        textCallBack = ""

        testRule.setContent {
            AppValidationTheme {
                FullNameFormField(
                    validator = Validator(),
                    onValueChange = { text, isValid ->
                        textCallBack = text
                        isValidCallBack = isValid
                    },
                )
            }
        }
    }

    private fun validateFullName(writeText: String, expectedIsValid: Boolean, delay: Long = 2000) {
        setFullNameFormField()

        // Sets the TextField value
        testRule.onNodeWithTag(FULL_NAME_FIELD_TAG).performTextInput(writeText)
        // Waits for the composition to be committed
        testRule.waitForIdle()
        AsyncTimer.asyncTimer(testRule, delay)
        if (expectedIsValid) {
            // Asserts the error message ID is NOT displayed
            testRule.onNodeWithContentDescription(ERROR_MESSAGE_ID).assertDoesNotExist()
        } else {
            // Asserts the error message ID is displayed
            testRule.onNodeWithContentDescription(ERROR_MESSAGE_ID).assertExists()
        }
        // Asserts the TextField has the corresponding value
        testRule.onNodeWithTag(FULL_NAME_FIELD_TAG).assert(hasText(textCallBack))
    }

    @Test
    fun fullName_validWhenOneLetter() {
        validateFullName("r",true)
    }

    @Test
    fun fullName_notValidWhenNoLetter() {
        validateFullName("",false)
    }

    @Test
    fun fullName_validWhenLetterAndOtherChars() {
        validateFullName("Kerollos202",true)
    }
}