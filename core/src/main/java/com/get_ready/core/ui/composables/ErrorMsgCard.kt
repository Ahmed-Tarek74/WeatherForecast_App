package com.get_ready.core.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.get_ready.core.ui.theme.Dimensions.BORDER_WIDTH
import com.get_ready.core.ui.theme.Dimensions.CARD_CORNER_RADIUS
import com.get_ready.core.ui.theme.Dimensions.INNER_CARD_ELEVATION
import com.get_ready.core.ui.theme.Dimensions.MEDIUM_PADDING
import com.get_ready.core.ui.theme.Weather_AppTheme
import com.get_ready.core.ui.theme.light_red
import com.get_ready.core.ui.theme.pink
import com.get_ready.core.ui.theme.red

@Composable
fun ErrorMsgCard(
    errorMsg: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = colorScheme.pink,
    textColor: Color = colorScheme.red,
    borderColor: Color = colorScheme.light_red,
    cornerRadius: Dp = CARD_CORNER_RADIUS,
    padding: Dp = MEDIUM_PADDING,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        modifier = modifier,
        shape = RoundedCornerShape(cornerRadius),
        border = BorderStroke(
            width = BORDER_WIDTH,
            color = borderColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = INNER_CARD_ELEVATION)
    ) {
        Text(
            text = errorMsg,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = textColor
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ErrorMsgCardPreview() {
    Weather_AppTheme {

        ErrorMsgCard(
            errorMsg = "Failed to get current weather please, check your connection",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp, horizontal = 24.dp)
        )
    }
}