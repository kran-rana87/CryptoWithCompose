package com.karan.coingecko.demo.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.karan.coingecko.demo.ui.MultiPreview

@Composable
fun CoinGeckoRoundedCornerButton(title: String, onClick: () -> Unit) {

    OutlinedButton(
        onClick = { onClick() },
        border = BorderStroke(
            1.dp,
            color = Teal90
        ),
        shape = RoundedCornerShape(50), modifier = Modifier.width(200.dp),

        ) {
        Text(text = title, style = TextStyle(color = Teal90))
    }
}

@Composable
fun CoinGeckoEditField(
    state: MutableState<String>,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {


    TextField(
        modifier = Modifier.width(300.dp),
        value = state.value,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            textColor = MaterialTheme.colors.onBackground,
            disabledTextColor = Color.Gray,
            cursorColor = Color.Gray,

            focusedIndicatorColor = Teal90,
            unfocusedIndicatorColor = Color.Gray,
        ),
        onValueChange = {
            state.value = it
        },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
    )

}

@Composable
fun CoinGeckoEditField(
    text: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit,
) {

    TextField(
        modifier = Modifier.width(300.dp),
        value = text,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            textColor = MaterialTheme.colors.onBackground,
            disabledTextColor = Color.Gray,
            cursorColor = Color.Gray,

            focusedIndicatorColor = Teal90,
            unfocusedIndicatorColor = Color.Gray,
        ),
        onValueChange = { onValueChange(it) },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
    )

}

@MultiPreview
@Composable
fun RoundedCornerButtonPreview() {
    CoinGeckoRoundedCornerButton("Button Title", onClick = {})
}

@SuppressLint("UnrememberedMutableState")
@MultiPreview
@Composable
fun CoinGeckoEditFieldPreview() {
    CoinGeckoEditField(mutableStateOf(""))
}
