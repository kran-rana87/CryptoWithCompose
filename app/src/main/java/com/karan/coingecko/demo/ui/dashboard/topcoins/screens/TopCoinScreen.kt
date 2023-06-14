package com.karan.coingecko.demo.ui.dashboard.topcoins.screens

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.karan.coingecko.demo.domain.models.Coin
import com.karan.coingecko.demo.domain.models.TopCoinsData
import com.karan.coingecko.demo.ui.AuthenticatedContentOrLogin
import com.karan.coingecko.demo.ui.CoinGeckoAppBar
import com.karan.coingecko.demo.ui.MultiPreview
import com.karan.coingecko.demo.ui.auth.screens.LoginState
import com.karan.flow.demo.R

@Composable
internal fun TopCoinsRoute(
    topCoinsViewModel: TopCoinsViewModel = hiltViewModel(),
    authState: State<LoginState>,
    navigateToLogin: () -> Unit,
    coinDetailRoute: String,
    navigateToCoinDetail: (String, String) -> Unit
) {
    val coinData by topCoinsViewModel.coinData.collectAsStateWithLifecycle()
    AuthenticatedContentOrLogin(authState, navigateToLogin) {
        TopCoinsScreen(
            coinState = coinData,
            topCoinsViewModel::sort,
            coinDetailRoute,
            navigateToCoinDetail
        )
    }
}

@Composable
internal fun TopCoinsScreen(
    coinState: TopCoinsUiState,
    sort: (TopCoinsViewModel.SortingMode) -> Unit = {},
    coinDetailRoute: String,
    navigateToCoinDetail: (String, String) -> Unit
) {
    Scaffold() { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (coinState) {
                is TopCoinsUiState.Success -> {
                    CoinList(coinState, sort, coinDetailRoute, navigateToCoinDetail)
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
fun CoinList(
    coinState: TopCoinsUiState.Success,
    sort: (TopCoinsViewModel.SortingMode) -> Unit,
    coinDetailRoute: String,
    navigateToCoinDetail: (String, String) -> Unit,
) {
    val listState = rememberLazyListState()
    LazyColumn(state = listState) {
        stickyHeader {
            ListHeader(sort = sort)
        }
        items(coinState.feed.coinListDashboard, key = { it.id }) {
            CoinRow(itemScope = it, coinDetailRoute, navigateToCoinDetail)
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
    sort: (TopCoinsViewModel.SortingMode) -> Unit,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        StickyHeaderText(
            title = "Coinas",
            onClick = { sort(TopCoinsViewModel.SortingMode.NAME_ASC) },
        )
        StickyHeaderText(
            title = "Price(AUD)",
            onClick = { sort(TopCoinsViewModel.SortingMode.PRICE_ASC) },
        )
        StickyHeaderText(
            title = "Change(24 hr)",
        )
    }
}


@Composable
internal fun StickyHeaderText(
    title: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    onClick: () -> Unit = { }
) {
    val style =
        textStyle.copy(MaterialTheme.colors.onBackground)
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.clickable {
            onClick()
        }) {
        Text(
            text = title,
            modifier = modifier,
            style = style,
        )
    }
}

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
internal fun CoinRow(
    itemScope: Coin,
    coinDetailRoute: String,
    navigateToCoinDetail: (String, String) -> Unit
) {
    val coinNameContentDesc = stringResource(id = R.string.coin_name, itemScope.name)
    val coinPriceDesc = stringResource(id = R.string.coin_price, itemScope.price)

    Row(
        modifier = Modifier
            .semantics(mergeDescendants = true) { }
            .fillMaxWidth()
            .padding(20.dp)
            .clickable {
                navigateToCoinDetail(coinDetailRoute, itemScope.id.toString())
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,

        ) {
        CoinImageResource(headerImageUrl = itemScope.imageURL)

        CoinText(title = itemScope.name, contentDescription = coinNameContentDesc)

        CoinText(title = itemScope.price, contentDescription = coinPriceDesc)

        CoinPriceChange(
            change24Hour = itemScope.change24hr.toString(),
            isPositiveChange = itemScope.isPositiveChange
        )

        CoinAddToFav()
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
        contentDescription = null, // decorative image
    )
}

@Composable
internal fun CoinText(
    title: String, contentDescription: String
) {
    Text(
        text = title,
        modifier = Modifier
            .padding(10.dp)
            .width(80.dp)
            .semantics { this.contentDescription = contentDescription },
    )
}

@Composable
internal fun CoinPriceChange(
    change24Hour: String,
    isPositiveChange: Boolean
) {
    val change =
        stringResource(id = R.string.hour_24_positive_change, change24Hour)
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .width(80.dp)
            .height(30.dp)
            .clip(CircleShape)
            .background(
                color = if (isPositiveChange) colorResource(id = R.color.green_90)
                else colorResource(id = R.color.red_90)
            )
    ) {

        Text(
            text = "$change24Hour%",
            modifier = Modifier
                .padding(5.dp)
                .semantics {
                    this.contentDescription = change

                }, style = TextStyle(
                color = Color.White, fontSize = 10.sp
            )
        )
        Icon(
            tint = Color.White,
            imageVector = if (isPositiveChange)
                Icons.Outlined.KeyboardArrowUp else
                Icons.Outlined.KeyboardArrowDown,
            contentDescription = null
        )
    }
}

@Composable
fun CoinAddToFav() {
    val checkedState = remember { mutableStateOf(false) }

    IconToggleButton(
        checked = checkedState.value,
        onCheckedChange = {
            checkedState.value = !checkedState.value
        },
    ) {
        Icon(
            imageVector = if (checkedState.value) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = stringResource(id = R.string.add_to_fav),
        )
    }
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
        ),
        coinDetailRoute = "",
        navigateToCoinDetail = { _, _ -> },
    )
}


