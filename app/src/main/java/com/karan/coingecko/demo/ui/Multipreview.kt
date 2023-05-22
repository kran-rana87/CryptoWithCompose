package com.karan.coingecko.demo.ui

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "potrait-phone",
    device = Devices.PIXEL_4_XL
)
@Preview(
    name = "landscape-phone",
    device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480",
)
@Preview(
    name = "tablet",
    device = Devices.TABLET
)
annotation class Multipreview
