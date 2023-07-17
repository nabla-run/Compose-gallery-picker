package run.nabla.gallerypicker.picker.result

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import run.nabla.gallerypicker.picker.GalleryPickerActivity

const val EXTRA_BACKGROUND_COLOR = "run.nabla.gallerypicker.intent.extra.BACKGROUND_COLOR"
const val EXTRA_TITLE_COLOR = "run.nabla.gallerypicker.intent.extra.TITLE_COLOR"
const val EXTRA_TITLE = "run.nabla.gallerypicker.intent.extra.TITLE"
const val EXTRA_TITLE_SIZE = "run.nabla.gallerypicker.intent.extra.TITLE_SIZE"
const val EXTRA_SHOW_EXIT_ACTION = "run.nabla.gallerypicker.intent.extra.SHOW_EXIT_ACTION"
const val EXTRA_HORIZONTAL_PADDING = "run.nabla.gallerypicker.intent.extra.HORIZONTAL_PADDING"
const val EXTRA_ITEM_ROUNDED_CORNER_SIZE =
    "run.nabla.gallerypicker.intent.extra.ITEM_ROUNDED_CORNER_SIZE"
const val EXTRA_GRID_COLUMNS = "run.nabla.gallerypicker.intent.extra.GRID_COLUMNS"
const val EXTRA_ITEM_MIN_HEIGHT = "run.nabla.gallerypicker.intent.extra.ITEM_MIN_HEIGHT"
const val EXTRA_ITEM_MAX_HEIGHT = "run.nabla.gallerypicker.intent.extra.ITEM_MAX_HEIGHT"
const val EXTRA_HEADER_PADDING_VERTICAL =
    "run.nabla.gallerypicker.intent.extra.HEADER_PADDING_VERTICAL"
const val EXTRA_HEADER_PADDING_HORIZONTAL =
    "run.nabla.gallerypicker.intent.extra.HEADER_PADDING_HORIZONTAL"

const val RESULT_URI = "run.nabla.gallerypicker.intent.result.URI"


open class GalleryContract : ActivityResultContract<GalleryRequest, Uri?>() {

    override fun createIntent(context: Context, input: GalleryRequest): Intent {
        return Intent(context, GalleryPickerActivity::class.java).apply {
            putExtra(EXTRA_BACKGROUND_COLOR, input.backgroundColor)
            putExtra(EXTRA_TITLE_COLOR, input.titleColor)
            putExtra(EXTRA_TITLE, input.title)
            putExtra(EXTRA_TITLE_SIZE, input.titleSize)
            putExtra(EXTRA_HEADER_PADDING_VERTICAL, input.titlePaddingVertical)
            putExtra(EXTRA_HEADER_PADDING_HORIZONTAL, input.titlePaddingHorizontal)
            putExtra(EXTRA_SHOW_EXIT_ACTION, input.showExitAction)
            putExtra(EXTRA_HORIZONTAL_PADDING, input.horizontalPadding)
            putExtra(EXTRA_ITEM_ROUNDED_CORNER_SIZE, input.itemsRoundedCornerSize)
            putExtra(EXTRA_GRID_COLUMNS, input.gridColumns)
            putExtra(EXTRA_ITEM_MIN_HEIGHT, input.itemMinHeight)
            putExtra(EXTRA_ITEM_MAX_HEIGHT, input.itemMaxHeight)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
        return intent.takeIf { resultCode == Activity.RESULT_OK }?.run {
            intent?.getStringExtra(RESULT_URI)?.let {
                Uri.parse(it)
            }
        }
    }

    fun fromBundle(bundle: Bundle?): GalleryRequest =
        GalleryRequest().apply {
            backgroundColor = bundle?.getLong(EXTRA_BACKGROUND_COLOR) ?: backgroundColor
            titleColor = bundle?.getLong(EXTRA_TITLE_COLOR) ?: titleColor
            title = bundle?.getString(EXTRA_TITLE) ?: title
            titleSize = bundle?.getInt(EXTRA_TITLE_SIZE) ?: titleSize
            showExitAction = bundle?.getBoolean(EXTRA_SHOW_EXIT_ACTION) ?: showExitAction
            titlePaddingVertical =
                bundle?.getInt(EXTRA_HEADER_PADDING_VERTICAL) ?: titlePaddingVertical
            titlePaddingHorizontal =
                bundle?.getInt(EXTRA_HEADER_PADDING_HORIZONTAL) ?: titlePaddingHorizontal
            horizontalPadding = bundle?.getInt(EXTRA_HORIZONTAL_PADDING) ?: horizontalPadding
            itemsRoundedCornerSize =
                bundle?.getInt(EXTRA_ITEM_ROUNDED_CORNER_SIZE) ?: itemsRoundedCornerSize
            gridColumns = bundle?.getInt(EXTRA_GRID_COLUMNS) ?: gridColumns
            itemMinHeight = bundle?.getInt(EXTRA_ITEM_MIN_HEIGHT) ?: itemMinHeight
            itemMaxHeight = bundle?.getInt(EXTRA_ITEM_MAX_HEIGHT) ?: itemMaxHeight
        }
}
