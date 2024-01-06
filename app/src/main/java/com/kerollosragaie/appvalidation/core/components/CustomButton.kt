package com.kerollosragaie.appvalidation.core.components

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kerollosragaie.appvalidation.R
import com.kerollosragaie.appvalidation.features.auth.presentation.component.FormFieldsSemanticsDescription.SUBMIT_FORM_BUTTON


@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    color: Color = MaterialTheme.colorScheme.primary,
    cornerRadius: Dp = 0.dp,
    onClick: () -> Unit,
    @StringRes textId: Int,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    borderWidth: Dp = 0.dp,
    borderColor: Color = color,
    gradient: Brush? = null
) {
    val buttonGradientColors = Brush.horizontalGradient(
        listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.inversePrimary,
        )
    )

    Button(
        onClick = onClick,
        modifier = modifier
            .testTag(SUBMIT_FORM_BUTTON),
        enabled = enabled,
        contentPadding = PaddingValues(vertical = 5.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(cornerRadius),
        border = BorderStroke(width = borderWidth, color = borderColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(cornerRadius))
                .background(brush = gradient ?: buttonGradientColors)
                .then(modifier.padding(vertical = 5.dp)),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = stringResource(id = textId), style = textStyle)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PrevCustomButton() {
    CustomButton(
        textId = R.string.sign_up,
        onClick = {},
    )
}