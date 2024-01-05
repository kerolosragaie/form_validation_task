package com.kerollosragaie.appvalidation.core

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import com.kerollosragaie.appvalidation.core.theme.AppValidationTheme
import com.kerollosragaie.appvalidation.core.utils.validation.Validator
import com.kerollosragaie.appvalidation.features.auth.presentation.component.FormFieldsSemanticsDescription
import com.kerollosragaie.appvalidation.features.auth.presentation.component.FullNameFormField
import org.junit.Rule
import org.junit.Test

class CustomComponentsTesting {

    @get:Rule
    val testRule: ComposeContentTestRule = createComposeRule()

    private fun setFullNameFormField(){
        testRule.setContent {
            AppValidationTheme {
                FullNameFormField(
                    validator = Validator(),
                    onValueChange = { _, _ -> },
                )
            }
        }
    }

    @Test
    //Is error message hidden after only one letter written
    fun fullNameFormField_isErrorMessageHide() {
        val resultText = "r"

        setFullNameFormField()

        // Sets the TextField value
        testRule.onNodeWithTag(FormFieldsSemanticsDescription.FULL_NAME_FIELD_TAG).performTextInput(resultText)
        // Waits for the composition to be committed
        testRule.waitForIdle()
        // Asserts the error message ID is NOT displayed
        testRule.onNodeWithContentDescription(FormFieldsSemanticsDescription.ERROR_MESSAGE_ID).assertDoesNotExist()
        // Asserts the TextField has the corresponding value
        testRule.onNodeWithTag(FormFieldsSemanticsDescription.FULL_NAME_FIELD_TAG).assert(hasText(resultText))
    }

    @Test
    //Is error message show after hidden
    fun fullNameFormField_isErrorMessageShow() {
        val writeText = "Kerollos"
        val noWrittenText=""

        setFullNameFormField()

        // Sets the TextField value
        testRule.onNodeWithTag(FormFieldsSemanticsDescription.FULL_NAME_FIELD_TAG).performTextInput(writeText)
        testRule.waitForIdle()
        // Clear the TextField value
        testRule.onNodeWithTag(FormFieldsSemanticsDescription.FULL_NAME_FIELD_TAG).performTextClearance()
        testRule.waitForIdle()
        // Asserts the error message ID is displayed
        testRule.onNodeWithContentDescription(FormFieldsSemanticsDescription.ERROR_MESSAGE_ID).assertExists()
        // Asserts the TextField has the corresponding value
        testRule.onNodeWithTag(FormFieldsSemanticsDescription.FULL_NAME_FIELD_TAG).assert(hasText(noWrittenText))
    }
}