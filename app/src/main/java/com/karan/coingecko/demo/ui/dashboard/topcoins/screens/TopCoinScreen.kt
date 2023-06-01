package com.karan.coingecko.demo.ui.dashboard.topcoins.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.karan.coingecko.demo.domain.models.Coin
import com.karan.coingecko.demo.domain.models.TopCoinsData
import com.karan.coingecko.demo.ui.CoinGeckoAppBar
import com.karan.coingecko.demo.ui.MultiPreview
import com.karan.coingecko.demo.ui.AuthenticatedContentOrLogin
import com.karan.coingecko.demo.ui.auth.screens.LoginState
import com.karan.flow.demo.R

@Composable
internal fun TopCoinsRoute(
    topCoinsViewModel: TopCoinsViewModel = hiltViewModel(),
    authState: State<LoginState>,
    navigateToLogin: () -> Unit
) {
    val coinData by topCoinsViewModel.coinData.collectAsStateWithLifecycle()
    AuthenticatedContentOrLogin(authState, navigateToLogin) {
        TopCoinsScreen(coinState = coinData, topCoinsViewModel::sortByName)
    }
}


@Composable
internal fun TopCoinsScreen(
    coinState: TopCoinsUiState,
    sortByName: () -> Unit = {}
) {
    Scaffold(topBar = {

        CoinGeckoAppBar()
    }) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (coinState) {
                is TopCoinsUiState.Success -> {
                    CoinList(sortByName, coinState)
                }
                is TopCoinsUiState.Loading -> {
                    CoinLoading()
                }
                else -> {
                    CoinError()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CoinList(sortByName: () -> Unit, coinState: TopCoinsUiState.Success) {
    val listState = rememberLazyListState()
    LazyColumn(state = listState) {
        stickyHeader {
            ListHeader(sortByName = sortByName)
        }
        items(coinState.feed.coinListDashboard, key = { it.id }) {
            CoinRow(itemScope = it)
        }
    }
}

@Composable
fun CoinLoading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.secondaryVariant
        )
    }
}

@Composable
fun CoinError() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(60.dp),
            painter = painterResource(id = R.drawable.no_connectivity),
            contentDescription = "Error while showing Coins"
        )
    }
}

@Composable
internal fun ListHeader(
    sortByName: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .padding(10.dp),
    ) {
        StickyHeaderText(
            title = "Coin",
            modifier = Modifier
                .width(120.dp)
                .padding(start = 10.dp),
            onClick = sortByName
        )
        StickyHeaderText(
            title = "Price(AUD)",
            modifier = Modifier.width(100.dp)
        )
        StickyHeaderText(
            title = "Change(24 hr)",
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(textAlign = TextAlign.Right)
        )
    }
}

@Composable
internal fun StickyHeaderText(
    title: String,
    modifier: Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    onClick: () -> Unit = { }
) {
    var style =
        textStyle.copy(MaterialTheme.colors.onBackground)
    ClickableText(
        text = AnnotatedString(title),
        modifier = modifier,
        style = style,
    ) {
        onClick()

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun CoinRow(itemScope: Coin) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
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
            Text(
                text = itemScope.price,
                style = TextStyle(textAlign = TextAlign.End),
                modifier = Modifier
                    .padding(10.dp)
                    .width(80.dp)
            )

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
internal fun CoinImageResource(
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
@MultiPreview
fun TopCoinsScreenPreview() {
    TopCoinsScreen(
        coinState = TopCoinsUiState.Success(
            TopCoinsData(
                listOf(
                    Coin(1, "Bit", "37727.00", -12.3, "", 2.3, 2.3, false),
                    Coin(2, "Bit", "200.00", 2.3, "", 2.3, 2.3, true)
                )
            )
        )
    )
}


