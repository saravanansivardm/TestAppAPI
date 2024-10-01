package com.example.testappapi.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testappapi.R
import com.example.testappapi.model.IpResponse
import com.example.testappapi.util.SubTitleText

@Composable
fun IpLookUpDataListItem(ipResponse: IpResponse, bgColor: Color) {
    val listOfData = listOf(ipResponse)
    val listOfDataSize = listOfData[listOfData.size - 1]

    val displayAllKeyValues: String = stringResource(id = R.string.ip_txt) + " : \n" +
            stringResource(id = R.string.type_txt) + " \n" +
            stringResource(id = R.string.cont_code_txt) + " \n" +
            stringResource(id = R.string.cont_name_txt) + " \n" +
            stringResource(id = R.string.country_code_txt) + " \n" +
            stringResource(id = R.string.country_name_txt) + " \n" +
            stringResource(id = R.string.region_code_txt) + " \n" +
            stringResource(id = R.string.region_name_txt) + " \n" +
            stringResource(id = R.string.city_txt) + " \n" +
            stringResource(id = R.string.zip_txt) + " \n" +
            stringResource(id = R.string.lat_txt) + " \n" +
            stringResource(id = R.string.long_txt) + " \n" +
            stringResource(id = R.string.msa_txt) + " \n" +
            stringResource(id = R.string.dma_txt) + " \n" +
            stringResource(id = R.string.radius_txt) + " \n" +
            stringResource(id = R.string.ip_route_type_txt) + " \n" +
            stringResource(id = R.string.connection_type_txt) + " \n" +
            stringResource(id = R.string.geo_name_txt) + " \n" +
            stringResource(id = R.string.capital_txt) + " \n" +
            stringResource(id = R.string.flag_emoji_txt) + " \n" +
            stringResource(id = R.string.emoji_unicode_txt) + " \n" +
            stringResource(id = R.string.calling_code_txt) + " \n" +
            stringResource(id = R.string.is_eu_txt) + " \n"

    val displayAllPairValues: String = "${listOfDataSize.ip}\n" +
            "${listOfDataSize.type}\n" +
            "${listOfDataSize.continentCode}\n" +
            "${listOfDataSize.continentName}\n" +
            "${listOfDataSize.countryCode}\n" +
            "${listOfDataSize.countryName}\n" +
            "${listOfDataSize.regionCode}\n" +
            "${listOfDataSize.regionName}\n" +
            "${listOfDataSize.city}\n" +
            "${listOfDataSize.zip}\n" +
            "${listOfDataSize.latitude}\n" +
            "${listOfDataSize.longitude}\n" +
            "${listOfDataSize.msa}\n" +
            "${listOfDataSize.dma}\n" +
            "${listOfDataSize.radius}\n" +
            "${listOfDataSize.ipRoutingType}\n" +
            "${listOfDataSize.connectionType}\n" +
            "${listOfDataSize.location.geonameId}\n" +
            "${listOfDataSize.location.capital}\n" +
            "${listOfDataSize.location.countryFlagEmoji}\n" +
            "${listOfDataSize.location.countryFlagEmojiUnicode}\n" +
            "${listOfDataSize.location.callingCode}\n" +
            "${listOfDataSize.location.isEu}\n"

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .wrapContentHeight()
            .background(bgColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 24.dp)
                .fillMaxWidth()
                .background(bgColor),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SubTitleText(
                modifier = Modifier.wrapContentWidth(),
                text = displayAllKeyValues,
                textAlign = TextAlign.Left,
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                lineHeight = 24.sp
            )
            SubTitleText(
                modifier = Modifier.wrapContentWidth(),
                text = displayAllPairValues,
                textAlign = TextAlign.Right,
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                lineHeight = 24.sp
            )
        }
    }
}


















