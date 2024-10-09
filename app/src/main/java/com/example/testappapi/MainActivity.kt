package com.example.testappapi

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.testappapi.network.model.IpResponse
import com.example.testappapi.network.util.ConnectivityObserver
import com.example.testappapi.network.util.NetworkConnectivityObserver
import com.example.testappapi.network.util.Resource
import com.example.testappapi.network.util.SubTitleText
import com.example.testappapi.network.util.TitleText
import com.example.testappapi.view.IpLookUpDataListItem
import com.example.testappapi.viewmodel.IpLookUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var connectivityObserver: ConnectivityObserver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityObserver = NetworkConnectivityObserver(applicationContext)
        setContent {
            CallApi(connectivityObserver = connectivityObserver)
        }
    }
}

@Composable
fun CallApi(
    viewModel: IpLookUpViewModel = hiltViewModel(), connectivityObserver: ConnectivityObserver
) {
    val status by connectivityObserver.observe().collectAsState(
        initial = ConnectivityObserver.Status.Unavailable
    )
    val apiState by viewModel.getIpData.collectAsStateWithLifecycle()

    Surface(
        color = Color.White, modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color.White)
            ) {
                when (apiState) {
                    is Resource.Loading -> {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is Resource.Success -> {
                        if (status == ConnectivityObserver.Status.Available) {
                            Column(
                                modifier = Modifier.background(Color.White),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                TitleText(
                                    modifier = Modifier.padding(16.dp),
                                    textAlign = TextAlign.Center,
                                    text = stringResource(id = R.string.title_txt),
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 28.sp,
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(text = (apiState as Resource.Success<IpResponse>).data.ip)
                                IpLookUpDataListItem(
                                    ipResponse = (apiState as Resource.Success<IpResponse>).data,
                                    bgColor = Color.Transparent
                                )
                            }
                        } else {
                            NoNetworkCall()
                        }
                    }
                    is Resource.Error -> {
                        val errorMessage = (apiState as Resource.Error).message
                        Log.e("error_log", errorMessage)
                    }
                }
            }
        }
    }
}

@Composable
fun NoNetworkCall() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SubTitleText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 40.dp),
            text = stringResource(id = R.string.check_network_txt),
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            lineHeight = 28.sp
        )
    }
}
