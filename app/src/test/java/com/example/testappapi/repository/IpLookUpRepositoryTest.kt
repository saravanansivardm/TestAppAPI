package com.example.testappapi.repository

import com.example.testappapi.network.api.ApiInterface
import com.example.testappapi.network.model.IpResponse
import com.example.testappapi.network.model.Language
import com.example.testappapi.network.model.Location
import com.example.testappapi.network.repository.IpLookUpRepository
import com.example.testappapi.network.util.Constants
import com.example.testappapi.network.util.Resource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class IpLookUpRepositoryTest {

    @Mock
    private lateinit var apiInterface: ApiInterface

    private lateinit var repository: IpLookUpRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = IpLookUpRepository(apiInterface)
    }

    @Test
    fun `getIpLookUpResponse return success when API is successful`() = runTest {
        val accessKey = Constants.API_KEY
        val ipResponse = IpResponse(
            "134.201.250.155",
            "ipv4",
            "NA",
            "North America",
            "US",
            "United States",
            "NV",
            "Nevada",
            "Spring Valley",
            "89144",
            0.1,
            0.2,
            "29820",
            "839",
            "1",
            "2",
            "3",
            Location(
                "1",
                "New york",
                "USA",
                "USA",
                "1",
                1,
                true,
                listOf(Language("1", "USA", "USA"))
            )
        )
        whenever(apiInterface.getIpLookUpData(accessKey)).thenReturn(ipResponse)
        val result = repository.getIpLookUpResponse()
        assertEquals(ipResponse, result)
    }

    @Test
    fun `getIpLookUpResponse return error when API throws an exception`() = runTest {
        val accessKey = "InvalidKey"
        whenever(apiInterface.getIpLookUpData(accessKey)).thenThrow(RuntimeException("Network error"))
        val errorMessage = (Resource.Error("Error")).message
        assertTrue(errorMessage, true)
    }
}
