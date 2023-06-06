package com.karan.coingecko.demo.ui.dashboard.settings.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.karan.coingecko.demo.ui.CoinGeckoAppBar
import com.karan.coingecko.demo.ui.MultiPreview
import com.karan.coingecko.demo.ui.AuthenticatedContentOrLogin
import com.karan.coingecko.demo.ui.auth.screens.LoginState


@Composable
internal fun SettingsRoute(
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    authState: State<LoginState>,
    navigateToLogin: () -> Unit
) {
    AuthenticatedContentOrLogin(authState, navigateToLogin) {
        SettingsScreen(onLogoutClick = settingsViewModel::logout)
    }
}

@Composable
internal fun SettingsScreen(onLogoutClick: () -> Unit) {
    val darkModeState = rememberSaveable { mutableStateOf(true) }
    val logoutConfirmation = remember { mutableStateOf(false) }

    Scaffold() { padding ->
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
        onDismissRequest = { logoutConfirmation.value = false },
        title = { Text(text = title) },
        confirmButton = {
            TextButton(onClick = {
                logoutConfirmation.value = false
                onLogoutClick()
            })
            { Text(text = "OK", style = TextStyle(color = MaterialTheme.colors.onBackground)) }
        },
    )
}

@Composable
@MultiPreview
fun SettingsScreenPreview() {
    SettingsScreen { }
}