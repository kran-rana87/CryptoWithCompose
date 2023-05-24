package com.karan.coingecko.demo.ui.dashboard.settings.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.karan.coingecko.demo.ui.CoinGeckoAppBar
import com.karan.coingecko.demo.ui.MultiPreview


@Composable
internal fun SettingsRoute(settingsViewModel: SettingsViewModel = hiltViewModel()) {
    SettingsScreen(onLogoutClick = settingsViewModel::logout)
}

@Composable
internal fun SettingsScreen(onLogoutClick: () -> Unit) {
    val darkModeState = remember { mutableStateOf(true) }
    val logoutConfirmation = remember { mutableStateOf(false) }

    Scaffold(topBar = {
        CoinGeckoAppBar()
    }) { padding ->
        if (logoutConfirmation.value) {
            alertDialog(
                title = "Are you sure you want to Logout?",
                onLogoutClick,
                logoutConfirmation
            )
        }
        Box(modifier = Modifier.padding(padding)) {
            Column {
                Card(elevation = 5.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable {
                            logoutConfirmation.value = true
                        })
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
fun alertDialog(
    title: String,
    onLogoutClick: () -> Unit,
    logoutConfirmation: MutableState<Boolean>
) {
    AlertDialog(
        backgroundColor = MaterialTheme.colors.surface,
        onDismissRequest = { logoutConfirmation.value = false },
        title = { Text(text = title) },
        confirmButton = {
            TextButton(onClick = {
                logoutConfirmation.value = false
                onLogoutClick()
            })
            { Text(text = "OK") }
        },
    )
}

@Composable
@MultiPreview
fun SettingsScreenPreview() {
    SettingsScreen { }
}