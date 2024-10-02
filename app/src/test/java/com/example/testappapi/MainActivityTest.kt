package com.example.testappapi

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testappapi.model.IpResponse
import com.example.testappapi.model.Language
import com.example.testappapi.model.Location
import com.example.testappapi.util.ConnectivityObserver
import com.example.testappapi.viewmodel.IpLookUpViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Mock
    lateinit var connectivityObserver: ConnectivityObserver

    @Mock
    lateinit var ipLookUpViewModel: IpLookUpViewModel

    @Before
    fun setup() {
        hiltRule.inject()
        MockitoAnnotations.openMocks(this)
    }

    @SuppressLint("UnrememberedMutableState")
    @Composable
    @Test
    fun TestLoadingIndicatorDisplayedWhenDataIsLoading() {
        whenever(ipLookUpViewModel.isLoading).thenReturn(mutableStateOf(false))
        composeTestRule.setContent {
            CallApi(viewModel = ipLookUpViewModel, connectivityObserver = connectivityObserver)
        }
        composeTestRule.onNodeWithTag("LoadingIndicator").assertIsDisplayed()
    }

    @SuppressLint("UnrememberedMutableState")
    @Composable
    @Test
    fun TestNetworkUnavailableMessageDisplayedWhenNoConnectivity() {
        whenever(ipLookUpViewModel.isLoading).thenReturn(mutableStateOf(true))
        whenever(connectivityObserver.observe()).thenReturn(flowOf(ConnectivityObserver.Status.Unavailable))
        composeTestRule.setContent {
            CallApi(viewModel = ipLookUpViewModel, connectivityObserver = connectivityObserver)
        }
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.check_network_txt))
            .assertIsDisplayed()
    }

    @SuppressLint("UnrememberedMutableState")
    @Composable
    @Test
    fun TestApiDataDisplayedWhenDataIsAvailableAndNetworkConnected() {
        val mockData = IpResponse(
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

        whenever(ipLookUpViewModel.isLoading).thenReturn(mutableStateOf(true))
        whenever(ipLookUpViewModel.getIpData).thenReturn(MutableLiveData(mockData))
        whenever(connectivityObserver.observe()).thenReturn(flowOf(ConnectivityObserver.Status.Available))

        composeTestRule.setContent {
            CallApi(viewModel = ipLookUpViewModel, connectivityObserver = connectivityObserver)
        }
        composeTestRule.onNodeWithText("Mock IP").assertIsDisplayed()
    }
}
