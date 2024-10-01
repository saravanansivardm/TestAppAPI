package com.example.testappapi.model

import com.google.gson.annotations.SerializedName

data class IpResponse(
    @SerializedName("ip")
    val ip: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("continent_code")
    val continentCode: String,
    @SerializedName("continent_name")
    val continentName: String,
    @SerializedName("country_code")
    val countryCode: String,
    @SerializedName("country_name")
    val countryName: String,
    @SerializedName("region_code")
    val regionCode: String,
    @SerializedName("region_name")
    val regionName: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("zip")
    val zip: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("msa")
    val msa: String,
    @SerializedName("dma")
    val dma: String,
    @SerializedName("radius")
    val radius: Any,
    @SerializedName("ip_routing_type")
    val ipRoutingType: Any,
    @SerializedName("connection_type")
    val connectionType: Any,
    @SerializedName("location")
    val location: Location,
)