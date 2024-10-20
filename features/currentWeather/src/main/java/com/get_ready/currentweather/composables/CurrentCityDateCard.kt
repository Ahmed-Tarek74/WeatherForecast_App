package com.get_ready.currentweather.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.get_ready.core.ui.theme.Dimensions.SPACER_WIDTH
import com.get_ready.features.R

@Composable
fun CurrentCityDateCard(currentCity: String, currentDate: String, onEditCurrentCity: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(SPACER_WIDTH)) {
            Text(
                text = currentCity,
                style = MaterialTheme.typography.headlineMedium
            )
            Icon(
                painter = painterResource(R.drawable.edit_location_ic),
                contentDescription = stringResource(com.get_ready.currentweather.R.string.edit_current_city_icon),
                modifier = Modifier.clickable { onEditCurrentCity() }
            )
        }
        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
        Text(
            text = currentDate,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}