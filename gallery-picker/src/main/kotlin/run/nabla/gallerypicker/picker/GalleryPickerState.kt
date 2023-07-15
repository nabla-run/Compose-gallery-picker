package run.nabla.gallerypicker.picker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun rememberGalleryPickerState(
    horizontalPadding: Dp = 10.dp,
    roundedCornerSize: Dp = 10.dp,
    headerTitle: String = DEFAULT_HEADER_TITLE
): GalleryPickerState = rememberSaveable(saver = GalleryPickerState.Saver) {
    GalleryPickerState(
        horizontalPadding = horizontalPadding.value,
        roundedCornerSize = roundedCornerSize.value,
        headerTitle = headerTitle
    )
}

@Stable
class GalleryPickerState(
    val horizontalPadding: Float,
    val roundedCornerSize: Float,
    val headerTitle: String,
) {
    internal companion object {
        /**
         * The default [Saver] implementation for [GalleryPickerState].
         */
        val Saver: Saver<GalleryPickerState, *> = listSaver(
            save = {
                listOf<Any>(
                    it.horizontalPadding,
                    it.roundedCornerSize,
                    it.headerTitle
                )
            },
            restore = {
                GalleryPickerState(
                    horizontalPadding = it[0] as Float,
                    roundedCornerSize = it[1] as Float,
                    headerTitle = it[2] as String,
                )
            }
        )
    }
}