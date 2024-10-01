package com.example.testappapi

import android.annotation.SuppressLint
import android.os.Bundle
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testappapi.util.ConnectivityObserver
import com.example.testappapi.util.SubTitleText
import com.example.testappapi.util.NetworkConnectivityObserver
import com.example.testappapi.util.TitleText
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CallApi(
    viewModel: IpLookUpViewModel = hiltViewModel(), connectivityObserver: ConnectivityObserver
) {
    val status by connectivityObserver.observe().collectAsState(
        initial = ConnectivityObserver.Status.Unavailable
    )
    val getAllLookUpData = viewModel.getIpData.observeAsState()

    Surface(
        color = Color.White, modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                if (!viewModel.isLoading.value) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                } else if (viewModel.isLoading.value && status == ConnectivityObserver.Status.Available) {
                    viewModel.fetchDataFromApi()
                    Column(
                        modifier = Modifier.background(Color.White),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TitleText(
                            modifier = Modifier.padding(16.dp),
                            text = stringResource(id = R.string.title_txt),
                            textAlign = TextAlign.Center,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        getAllLookUpData.value?.let { lookUpData ->
                            IpLookUpDataListItem(
                                lookUpData,
                                Color.Transparent
                            )
                        }
                    }
                } else {
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
            }
        }
    }
}
