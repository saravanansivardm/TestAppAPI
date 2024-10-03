package com.example.testappapi.viewmodel

import com.example.testappapi.model.IpResponse
import com.example.testappapi.model.Language
import com.example.testappapi.model.Location
import com.example.testappapi.repository.IpLookUpRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.whenever

@HiltAndroidTest
@ExperimentalCoroutinesApi
class IpLookUpViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var viewModel: IpLookUpViewModel

    @Mock
    private lateinit var ipLookUpRepository: IpLookUpRepository

    @Before
    fun setUp() {
        hiltRule.inject()
        viewModel = IpLookUpViewModel(ipLookUpRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        viewModel.getIpData.value
    }

    @Test
    fun `fetchDataFromApi success when success`() = runTest {
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

        Mockito.`when`(ipLookUpRepository.getIpLookUpResponse()).thenReturn(ipResponse)
        val sut = IpLookUpViewModel(ipLookUpRepository)
        sut.fetchDataFromApi()
        advanceUntilIdle()
        val result = sut.getIpData.value
        assertEquals(viewModel.getIpData.value, result)
    }

    @Test
    fun `fetchDataFromApi error`() = runTest {
        whenever(ipLookUpRepository.getIpLookUpResponse()).thenThrow(
            RuntimeException("Network error")
        )
        viewModel.fetchDataFromApi()
        advanceUntilIdle()
        assertEquals(false, viewModel.isLoading.value)
    }
}
