package com.kerollosragaie.appvalidation.core.utils.validation.enums

import com.kerollosragaie.appvalidation.R

enum class PasswordValidationType(val stringResource: Int) {
    AT_LEAST_8 (R.string.the_password_needs_to_consist_of_at_least_8_characters),
    AT_LEAST_ONE_SPECIAL_CHAR (R.string.the_password_should_contain_at_least_special_character),
    AT_LEAST_ONE_LETTER_AND_DIGIT (R.string.the_password_needs_to_contain_at_least_one_letter_and_digit),
}