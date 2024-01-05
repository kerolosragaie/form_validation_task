package com.kerollosragaie.appvalidation.features.auth.presentation.components

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import com.kerollosragaie.appvalidation.core.theme.AppValidationTheme
import com.kerollosragaie.appvalidation.core.utils.validation.Validator
import com.kerollosragaie.appvalidation.features.auth.presentation.component.FormFieldsSemanticsDescription.ERROR_MESSAGE_ID
import com.kerollosragaie.appvalidation.features.auth.presentation.component.FormFieldsSemanticsDescription.MOB_NUMBER_FIELD_TAG
import com.kerollosragaie.appvalidation.features.auth.presentation.component.MobNumberFormField
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class FormFieldsTesting {
    @get:Rule
    val testRule: ComposeContentTestRule = createComposeRule()

    private fun setMobNumberFormField(){
        testRule.setContent {
            AppValidationTheme {
                MobNumberFormField(
                    validator = Validator(),
                    onValueChange = { _, _ ->

                    },
                )
            }
        }
    }

    @Test
    fun mobNumberFormField_isValidating(){
        val mobNumber = "12345678912"

        setMobNumberFormField()

        // Sets the TextField value
        testRule.onNodeWithTag(MOB_NUMBER_FIELD_TAG).performTextInput(mobNumber)
        testRule.waitForIdle()
        // Is error message not appeared?
        testRule.onNodeWithTag(ERROR_MESSAGE_ID).assertDoesNotExist()
        //Is numbers length is 11? and message not appeared?
        testRule.onNodeWithTag(MOB_NUMBER_FIELD_TAG).assertTextEquals("Mobile number",mobNumber)
    }


    @Test
    fun mobNumberFormField_CallbackCorrectValuesOnChange(){
        val textFieldHintName = "Mobile number"
        val mobNumber = "12345678912"

        var writtenText =""
        var isValidMobNumber = false

        testRule.setContent {
            AppValidationTheme {
                MobNumberFormField(
                    validator = Validator(),
                    onValueChange = { text, isValid ->
                        writtenText = text
                        isValidMobNumber = isValid
                    },
                )
            }
        }

        // Sets the TextField value
        testRule.onNodeWithTag(MOB_NUMBER_FIELD_TAG).performTextInput(mobNumber)
        testRule.waitForIdle()
        // Is error message not appeared?
        testRule.onNodeWithTag(ERROR_MESSAGE_ID).assertDoesNotExist()
        //Is numbers length is 11? and call back text param is matching written text?
        testRule.onNodeWithTag(MOB_NUMBER_FIELD_TAG).assertTextEquals(textFieldHintName,writtenText)
        //Is call back isValid param == true
        assertEquals(true,isValidMobNumber)
    }
}