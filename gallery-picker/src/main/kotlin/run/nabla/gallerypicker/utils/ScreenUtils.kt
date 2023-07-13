package run.nabla.gallerypicker.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun getScreenSize(): Size {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    configuration.screenLayout
    val widthInPx = with(density) { configuration.screenWidthDp.dp.roundToPx() }
    val heightInPx = with(density) { configuration.screenHeightDp.dp.roundToPx() }
    return Size(
        width = widthInPx.toFloat(),
        height = heightInPx.toFloat()
    )
}