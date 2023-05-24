package com.karan.coingecko.demo.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.karan.flow.demo.R
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.karan.coingecko.demo.ui.MultiPreview

@Composable
fun CoinGeckoRoundedCornerButton(title: String, onClick: () -> Unit) {
/*    Button(onClick = { onClick() },
            shape = RoundedCornerShape(50.dp), modifier = Modifier
            .height(50.dp)
            .width(200.dp),
            colors = ButtonDefaults.buttonColors(
                    backgroundColor =
                    MaterialTheme.colors.primarySurface)

    ) {
        Text(text = title)
    }*/

    OutlinedButton(
            onClick = { onClick() },
            border = BorderStroke(1.dp,
                    color = Teal90),
            shape = RoundedCornerShape(50), modifier = Modifier.width(200.dp),

    ) {
        Text(text = title, style = TextStyle(color = Teal90))
    }
}


@Composable
fun CoinGeckoEditField(title: String,
                       state: MutableState<String>,
                       keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
                       visualTransformation: VisualTransformation = VisualTransformation.None) {
/*    OutlinedTextField(
            label = { Text(text = title) },
            value = state.value,
            onValueChange = { state.value = it },
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = colorResource
                    (id = R.color.teal_700),
                    focusedBorderColor = colorResource
                    (id = R.color.teal_700),
                    focusedLabelColor = colorResource
                    (id = R.color.teal_700)))*/


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
            onValueChange = { state.value = it },
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
    CoinGeckoEditField("EditField", mutableStateOf(""))
}
