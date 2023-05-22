package com.karan.coingecko.demo.ui.dashboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.karan.coingecko.demo.domain.models.Coin
import com.karan.coingecko.demo.domain.models.TopCoinsUIData
import com.karan.coingecko.demo.ui.CoinGeckoAppBar
import com.karan.coingecko.demo.ui.Multipreview
import com.karan.flow.demo.R

@Composable
fun TopCoinsRoute(topCoinsViewModel: TopCoinsViewModel = hiltViewModel()) {
    val coinData by topCoinsViewModel.coinData.collectAsStateWithLifecycle()
    TopCoinsScreen(state = coinData)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopCoinsScreen(
    state: TopCoinsUiState,
) {
    Scaffold(topBar = {
        CoinGeckoAppBar()
    }) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (state) {
                is TopCoinsUiState.Success -> {
                    val listState = rememberLazyListState()

                    LazyColumn(state = listState) {
                        stickyHeader {
                            ListHeader()
                        }
                        items(state.feed.coinListDashboard, key = { it.id }) {
                            CoinRow(itemScope = it)
                        }
                    }
                }
                is TopCoinsUiState.Loading -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                }
                else -> {
                    Text(text = "Welcome to Dashboard")
                }
            }
        }
    }
}


@Composable
fun ListHeader(
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = "Coin")
        Text(text = "Price(AUD)")
        Text(text = "Change(24 hr)")
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinRow(itemScope: Coin) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(

            verticalAlignment = Alignment.CenterVertically,
        ) {
            CoinImageResource(headerImageUrl = itemScope.imageURL)
            Text(
                text = itemScope.name, modifier = Modifier
                    .padding(10.dp)
                    .width(50.dp)
            )
            Text(text = itemScope.price, modifier = Modifier.padding(10.dp))

        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(80.dp)
                .height(30.dp)
                .clip(CircleShape)
                .background(
                    color =
                    if (itemScope.isPositiveChange) colorResource(id = R.color.green_90)
                    else colorResource(id = R.color.red_90)
                )
        ) {
            Text(
                text = itemScope.change24hr.toString() + "%",
                modifier = Modifier.padding(5.dp), style = TextStyle(
                    color = Color.White, fontSize = 10.sp
                )
            )
            Icon(
                tint = Color.White,
                imageVector = if (itemScope.isPositiveChange)
                    Icons.Outlined.KeyboardArrowUp else
                    Icons.Outlined.KeyboardArrowDown,
                contentDescription = "Change Percentage"
            )
        }

    }
}


@Composable
fun CoinImageResource(
    headerImageUrl: String?,
) {
    AsyncImage(
        placeholder = if (LocalInspectionMode.current) {
            painterResource(R.drawable.bg_account_dashboard)
        } else {
            null
        },
        modifier = Modifier
            .width(40.dp)
            .height(40.dp),
        contentScale = ContentScale.Fit,
        model = headerImageUrl,
        contentDescription = "Coin Image", // decorative image
    )
}

@Composable
@Multipreview
fun TopCoinsScreenPreview() {
    TopCoinsScreen(
        state = TopCoinsUiState.Success(
            TopCoinsUIData(
                listOf(
                    Coin(1, "Bit", "200.00", -12.3, "", 2.3, 2.3, false),
                    Coin(2, "Bit", "200.00", 2.3, "", 2.3, 2.3, true)
                )
            )
        )
    )
}

