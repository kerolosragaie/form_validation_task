package com.kerollosragaie.appvalidation.core.utils.validation.state

import androidx.annotation.StringRes
import com.kerollosragaie.appvalidation.core.components.TextFieldType
import com.kerollosragaie.appvalidation.core.utils.validation.interfaces.TextFieldId

data class ValidationState(
    var text: String = "",
    var textFieldType: TextFieldType = TextFieldType.Text,
    val id: TextFieldId,
    val isRequired: Boolean = true,
    var hasError: Boolean = true,
    @StringRes val errorMessageId: Int? = null,
)
