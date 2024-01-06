package com.kerollosragaie.appvalidation.features.auth.presentation.components

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import com.kerollosragaie.appvalidation.AsyncTimer
import com.kerollosragaie.appvalidation.core.theme.AppValidationTheme
import com.kerollosragaie.appvalidation.core.utils.validation.Validator
import com.kerollosragaie.appvalidation.features.auth.presentation.component.FormFieldsSemanticsDescription.ERROR_MESSAGE_ID
import com.kerollosragaie.appvalidation.features.auth.presentation.component.FormFieldsSemanticsDescription.MOB_NUMBER_FIELD_TAG
import com.kerollosragaie.appvalidation.features.auth.presentation.component.MobNumberFormField
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MobNumberFormFieldTesting {
    @get:Rule
    val testRule: ComposeContentTestRule = createComposeRule()

    //Text field hint
    private val textFieldHintName = "Mobile number"

    //call-back variables
    private var textCallBack = ""
    private var isValidCallBack: Boolean = false

    private fun setMobNumberFormField() {
        isValidCallBack = false

        testRule.setContent {
            AppValidationTheme {
                MobNumberFormField(
                    validator = Validator(),
                    onValueChange = { text, isValid ->
                        textCallBack = text
                        isValidCallBack = isValid
                    },
                )
            }
        }
    }

    private fun mobileIsValid(
        mobNumber: String,
        expectedToBeValid: Boolean,
        delay: Long = 3000,
    ) {
        setMobNumberFormField()
        // Sets the TextField value
        testRule.onNodeWithTag(MOB_NUMBER_FIELD_TAG).performTextInput(mobNumber)
        testRule.waitForIdle()
        AsyncTimer.asyncTimer(testRule, delay)
        if (expectedToBeValid) {
            // Is error message not appeared?
            testRule.onNodeWithTag(ERROR_MESSAGE_ID).assertDoesNotExist()
        } else {
            // Is error message appeared?
            testRule.onNodeWithTag(ERROR_MESSAGE_ID).assertExists()
        }
        //Is textCallBack == entered mobNumber
        testRule.onNodeWithTag(MOB_NUMBER_FIELD_TAG)
            .assertTextEquals(textFieldHintName, textCallBack)
        //Is call back isValid param == expectedToBeValid
        assertEquals(expectedToBeValid, isValidCallBack)
    }


    @Test
    fun mobile_lessThen11() {
        mobileIsValid("1234567891", false)
    }

    @Test
    fun mobile_EqualTo11() {
        mobileIsValid("12345678912", true)
    }

    @Test
    fun mobile_MoreThen11() {
        mobileIsValid("123456789123", false)
    }

    @Test
    fun mobile_containsOtherValuesThenNumbers() {
        mobileIsValid("1234567891a", false)
    }
}