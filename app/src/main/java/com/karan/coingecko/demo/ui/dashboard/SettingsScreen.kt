package com.karan.coingecko.demo.ui.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.karan.coingecko.demo.ui.CoinGeckoAppBar
import com.karan.coingecko.demo.ui.MultiPreview


@Composable
fun SettingsRoute(settingsViewModel: SettingsViewModel = hiltViewModel()) {
    SettingsScreen(onLogoutClick = settingsViewModel::logout)
}

@Composable
fun SettingsScreen(onLogoutClick: () -> Unit) {
    val darkModeState = remember { mutableStateOf(true) }

    Scaffold(topBar = {
        CoinGeckoAppBar()
    }) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            Column {
                Card(elevation = 5.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable { onLogoutClick() })
                {
                    Row(modifier = Modifier.padding(20.dp)) {
                        Text(text = "Logout")
                    }
                }
                Card(
                    elevation = 5.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)

                ) {
                    Column {

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "DarkMode")
                            Switch(checked = darkModeState.value, onCheckedChange = {
                                darkModeState.value = it
                            })
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Profile")
                            Icon(Icons.Outlined.KeyboardArrowRight, contentDescription = null)
                        }
                    }
                }
            }
        }
    }
}

@Composable
@MultiPreview
fun SettingsScreenPreview() {
    SettingsScreen { }
}