package com.example.testappapi.view

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testappapi.model.IpResponse
import com.example.testappapi.model.Language
import com.example.testappapi.model.Location
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IpLookUpDataListItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testIpLookUpDataListItemDisplayedCorrectly() {
        val ipResponse = IpResponse(
            ip = "192.168.0.1",
            type = "IPv4",
            continentCode = "NA",
            continentName = "North America",
            countryCode = "US",
            countryName = "United States",
            regionCode = "CA",
            regionName = "California",
            city = "San Francisco",
            zip = "94107",
            latitude = 37.7749,
            longitude = -122.4194,
            msa = "41860",
            dma = "807",
            radius = "10",
            ipRoutingType = "Public",
            connectionType = "WiFi",
            location = Location(
                geonameId = 123456,
                capital = "Washington D.C.",
                countryFlagEmoji = "🇺🇸",
                countryFlagEmojiUnicode = "U+1F1FA U+1F1F8",
                callingCode = "1",
                isEu = false,
                languages = listOf(Language(code = "1", name = "usa", native = "usa")),
                countryFlag = "flag"
            )
        )

        composeTestRule.setContent {
            IpLookUpDataListItem(ipResponse = ipResponse, bgColor = Color.White)
        }

        composeTestRule.onNodeWithText("IP Address :").assertIsDisplayed()
        composeTestRule.onNodeWithText("192.168.0.1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Type :").assertIsDisplayed()
        composeTestRule.onNodeWithText("IPv4").assertIsDisplayed()
        composeTestRule.onNodeWithText("Continent Code :").assertIsDisplayed()
        composeTestRule.onNodeWithText("NA").assertIsDisplayed()
        composeTestRule.onNodeWithText("Continent Name :").assertIsDisplayed()
        composeTestRule.onNodeWithText("North America").assertIsDisplayed()
        composeTestRule.onNodeWithText("Country Code :").assertIsDisplayed()
        composeTestRule.onNodeWithText("US").assertIsDisplayed()
        composeTestRule.onNodeWithText("Country Name :").assertIsDisplayed()
        composeTestRule.onNodeWithText("United States").assertIsDisplayed()
        composeTestRule.onNodeWithText("Region Code :").assertIsDisplayed()
        composeTestRule.onNodeWithText("CA").assertIsDisplayed()
        composeTestRule.onNodeWithText("City :").assertIsDisplayed()
        composeTestRule.onNodeWithText("San Francisco").assertIsDisplayed()
        composeTestRule.onNodeWithText("Latitude :").assertIsDisplayed()
        composeTestRule.onNodeWithText("37.7749").assertIsDisplayed()
        composeTestRule.onNodeWithText("Longitude :").assertIsDisplayed()
        composeTestRule.onNodeWithText("-122.4194").assertIsDisplayed()
        composeTestRule.onNodeWithText("Capital :").assertIsDisplayed()
        composeTestRule.onNodeWithText("Washington D.C.").assertIsDisplayed()
        composeTestRule.onNodeWithText("🇺🇸").assertIsDisplayed()
        composeTestRule.onNodeWithText("U+1F1FA U+1F1F8").assertIsDisplayed()
        composeTestRule.onRoot().assert(hasText("192.168.0.1"))
    }
}
