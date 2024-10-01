package com.example.testappapi.model

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("calling_code")
    val callingCode: String,
    @SerializedName("capital")
    val capital: String,
    @SerializedName("country_flag")
    val countryFlag: String,
    @SerializedName("country_flag_emoji")
    val countryFlagEmoji: String,
    @SerializedName("country_flag_emoji_unicode")
    val countryFlagEmojiUnicode: String,
    @SerializedName("geoname_id")
    val geonameId: Int,
    @SerializedName("is_eu")
    val isEu: Boolean,
    @SerializedName("languages")
    val languages: List<Language>
)