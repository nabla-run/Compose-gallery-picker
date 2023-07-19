package run.nabla.gallerypicker.picker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable


const val DEFAULT_HORIZONTAL_PADDING = 10
const val DEFAULT_ITEM_ROUNDED_CORNER_SIZE = 10
const val DEFAULT_GRID_COLUMNS = 3
const val DEFAULT_ITEM_MIN_HEIGHT = 150
const val DEFAULT_ITEM_MAX_HEIGHT = 250

@Composable
fun rememberGalleryPickerState(
    horizontalPadding: Int = DEFAULT_HORIZONTAL_PADDING,
    roundedCornerSize: Int = DEFAULT_ITEM_ROUNDED_CORNER_SIZE,
    gridColumns: Int = DEFAULT_GRID_COLUMNS,
    itemMinHeight: Int = DEFAULT_ITEM_MIN_HEIGHT,
    itemMaxHeight: Int = DEFAULT_ITEM_MAX_HEIGHT,
): GalleryPickerState = rememberSaveable(saver = GalleryPickerState.Saver) {
    GalleryPickerState(
        horizontalPadding = horizontalPadding,
        roundedCornerSize = roundedCornerSize,
        gridColumns = gridColumns,
        itemMinHeight = itemMinHeight,
        itemMaxHeight = itemMaxHeight
    )
}

@Stable
class GalleryPickerState(
    val horizontalPadding: Int,
    val roundedCornerSize: Int,
    val gridColumns: Int,
    val itemMinHeight: Int,
    val itemMaxHeight: Int,
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
                    it.gridColumns,
                    it.itemMinHeight,
                    it.itemMaxHeight,
                )
            },
            restore = {
                GalleryPickerState(
                    horizontalPadding = it[0] as Int,
                    roundedCornerSize = it[1] as Int,
                    gridColumns = it[2] as Int,
                    itemMinHeight = it[3] as Int,
                    itemMaxHeight = it[4] as Int,
                )
            }
        )
    }
}
