package com.example.testappapi

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.testappapi.network.model.IpResponse
import com.example.testappapi.network.model.Language
import com.example.testappapi.network.model.Location
import com.example.testappapi.network.util.ConnectivityObserver
import com.example.testappapi.network.util.Resource
import com.example.testappapi.viewmodel.IpLookUpViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@HiltAndroidTest
@ExperimentalCoroutinesApi
class MainActivityTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createComposeRule()

    @Mock
    lateinit var mockViewModel: IpLookUpViewModel

    @Mock
    lateinit var connectivityObserver: ConnectivityObserver

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        hiltRule.inject()
        Mockito.`when`(connectivityObserver.observe())
            .thenReturn(MutableStateFlow(ConnectivityObserver.Status.Available))
    }

    @Test
    fun testLoadingState() {
        Mockito.`when`(mockViewModel.getIpData).thenReturn(MutableStateFlow(Resource.Loading))
        composeTestRule.setContent {
            CallApi(viewModel = mockViewModel, connectivityObserver = connectivityObserver)
        }
        composeTestRule.onNodeWithTag("loadingIndicator").assertIsDisplayed()
    }

    @Test
    fun testSuccessStateWithNetwork() {
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
        Mockito.`when`(mockViewModel.getIpData)
            .thenReturn(MutableStateFlow(Resource.Success(ipResponse)))
        composeTestRule.setContent {
            CallApi(viewModel = mockViewModel, connectivityObserver = connectivityObserver)
        }
        composeTestRule.onNodeWithText("192.168.1.1").assertIsDisplayed()
    }

    @Test
    fun testErrorState() {
        val errorMessage = "An error occurred"
        Mockito.`when`(mockViewModel.getIpData)
            .thenReturn(MutableStateFlow(Resource.Error(errorMessage)))
        composeTestRule.setContent {
            CallApi(viewModel = mockViewModel, connectivityObserver = connectivityObserver)
        }
    }

    @Test
    fun testNoNetworkCall() {
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
        Mockito.`when`(connectivityObserver.observe())
            .thenReturn(MutableStateFlow(ConnectivityObserver.Status.Unavailable))
        Mockito.`when`(mockViewModel.getIpData)
            .thenReturn(MutableStateFlow(Resource.Success(ipResponse)))
        composeTestRule.setContent {
            CallApi(viewModel = mockViewModel, connectivityObserver = connectivityObserver)
        }
        composeTestRule.onNodeWithText("Check your network connection").assertIsDisplayed()
    }
}
