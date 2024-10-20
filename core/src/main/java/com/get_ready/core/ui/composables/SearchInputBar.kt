package com.get_ready.core.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.get_ready.core.ui.theme.Dimensions.LOW_PADDING
import com.get_ready.core.ui.theme.Weather_AppTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.get_ready.core.R

@Composable
fun SearchInputBar(
    searchHint: String,
    searchQuery: String,
    modifier: Modifier = Modifier,
    onSearchQueryChanged: (String) -> Unit,
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChanged,
        singleLine = true,
        leadingIcon = { Icon(Icons.AutoMirrored.Filled.ArrowForward , contentDescription = stringResource(
            R.string.input_leading_icon
        )
        )},
        textStyle = MaterialTheme.typography.headlineMedium,
        placeholder = {
            Text(
                text = searchHint,
                style = MaterialTheme.typography.headlineMedium,
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text
        ),
        maxLines = 1,
        colors = getTextFieldColors(),
        modifier = modifier
    )
}

@Composable
fun getTextFieldColors() = TextFieldDefaults.colors(
    focusedTextColor = colorScheme.onSecondaryContainer,  // Text color when focused
    unfocusedTextColor = colorScheme.onSecondaryContainer,  // Text color when unfocused
    focusedContainerColor = colorScheme.secondaryContainer, // Background color when focused
    unfocusedContainerColor = colorScheme.secondaryContainer, // Background color when unfocused
    cursorColor = colorScheme.onSecondaryContainer,                    // Cursor color
    focusedIndicatorColor = Color.Transparent,            // Hides focused underline
    unfocusedIndicatorColor = Color.Transparent,          // Hides unfocused underline
    disabledIndicatorColor = Color.Transparent,         // Hides disabled underline
    errorIndicatorColor = Color.Transparent               // Hides error underline
)

@Composable
@Preview(showBackground = true)
fun SearchInputBarPreview() {
    Weather_AppTheme {
        SearchInputBar(
            searchHint = "Enter city name ..", searchQuery = "", modifier = Modifier
                .fillMaxWidth()
                .padding(LOW_PADDING)
        ) {}
    }
}