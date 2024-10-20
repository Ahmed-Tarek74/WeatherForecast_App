package com.get_ready.core.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.Dialog
import com.get_ready.core.ui.theme.Dimensions.CARD_CORNER_RADIUS
import com.get_ready.core.ui.theme.Dimensions.LOADING_DIALOG_DEFAULT_SIZE
import com.get_ready.core.ui.theme.Dimensions.MEDIUM_PADDING
import com.get_ready.core.ui.theme.Dimensions.SPACER_HEIGHT

@Composable
fun LoadingDialog(
    loadingMsg: String,
    onDismiss: () -> Unit = {},
    dialogSize: Dp = LOADING_DIALOG_DEFAULT_SIZE,
    cornerRadius: Dp = CARD_CORNER_RADIUS,
    padding: Dp = MEDIUM_PADDING,
    progressIndicatorColor: Color = colorScheme.onPrimaryContainer,
) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .size(dialogSize)
                .clip(RoundedCornerShape(cornerRadius))
                .background(colorScheme.secondaryContainer)
        ) {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(color = progressIndicatorColor)
                Spacer(modifier = Modifier.height(SPACER_HEIGHT))
                Text(
                    text = loadingMsg,
                    style = MaterialTheme.typography.headlineMedium.copy(color = progressIndicatorColor),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}