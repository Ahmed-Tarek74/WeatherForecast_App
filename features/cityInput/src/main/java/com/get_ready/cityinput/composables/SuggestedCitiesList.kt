package com.get_ready.cityinput.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.get_ready.cityinput.R
import com.get_ready.cityinput.model.CityUIModel
import com.get_ready.core.ui.theme.Dimensions.LOW_PADDING
import com.get_ready.core.ui.theme.Dimensions.MEDIUM_PADDING
import com.get_ready.core.ui.theme.Dimensions.MID_SPACER_WIDTH
import com.get_ready.core.ui.theme.light_gray
import com.get_ready.core.ui.theme.white


@Composable
fun SuggestedCitiesList(
    suggestedCities: List<CityUIModel>,
    modifier: Modifier = Modifier,
    onCitySelected: (String) -> Unit,
) {
    LazyColumn(modifier) {
        items(suggestedCities) { city ->
            SuggestedCityItemCard(city, onCitySelected = onCitySelected)
        }
    }
}

@Composable
fun SuggestedCityItemCard(suggestedCity: CityUIModel, onCitySelected: (String) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(MID_SPACER_WIDTH),
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .background(color = colorScheme.secondaryContainer, shape = RoundedCornerShape(15.dp))
            .clickable {
                onCitySelected(suggestedCity.cityName)
            }
            .padding(
                MEDIUM_PADDING
            )
    )
    {
        Icon(
            Icons.Default.LocationOn,
            contentDescription = stringResource(R.string.location_icon),
            tint = colorScheme.onSecondaryContainer,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Column {
            Text(
                text = suggestedCity.cityName,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = colorScheme.onSecondaryContainer
                )
            )
            Text(
                text = suggestedCity.country,
                style = MaterialTheme.typography.bodyLarge,
                color = colorScheme.onSecondaryContainer
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SuggestedCityItemCardPreview() {
    SuggestedCityItemCard(CityUIModel("Cairo", "Egypt")) {}
}