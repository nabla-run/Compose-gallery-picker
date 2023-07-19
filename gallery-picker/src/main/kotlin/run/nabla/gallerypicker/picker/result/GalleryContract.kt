package run.nabla.gallerypicker.picker.result

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import run.nabla.gallerypicker.picker.GalleryPickerActivity

const val EXTRA_FONT_FAMILY = "run.nabla.gallerypicker.intent.extra.FONT_FAMILY"
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

const val EXTRA_PERMISSION_TITLE =
    "run.nabla.gallerypicker.intent.extra.PERMISSION_TITLE"
const val EXTRA_PERMISSION_TITLE_COLOR =
    "run.nabla.gallerypicker.intent.extra.PERMISSION_TITLE_COLOR"
const val EXTRA_PERMISSION_TITLE_SIZE =
    "run.nabla.gallerypicker.intent.extra.PERMISSION_TITLE_SIZE"
const val EXTRA_PERMISSION_BODY =
    "run.nabla.gallerypicker.intent.extra.PERMISSION_BODY"
const val EXTRA_PERMISSION_BODY_SIZE =
    "run.nabla.gallerypicker.intent.extra.PERMISSION_BODY_SIZE"
const val EXTRA_PERMISSION_BODY_COLOR =
    "run.nabla.gallerypicker.intent.extra.PERMISSION_BODY_COLOR"
const val EXTRA_PERMISSION_BACKGROUND_COLOR =
    "run.nabla.gallerypicker.intent.extra.PERMISSION_BACKGROUND_COLOR"
const val EXTRA_PERMISSION_IMAGE =
    "run.nabla.gallerypicker.intent.extra.PERMISSION_IMAGE"
const val EXTRA_PERMISSION_PRIMARY_ACTION_TITLE =
    "run.nabla.gallerypicker.intent.extra.PRIMARY_ACTION_TITLE"
const val EXTRA_PERMISSION_PRIMARY_ACTION_COLOR =
    "run.nabla.gallerypicker.intent.extra.PRIMARY_ACTION_COLOR"
const val EXTRA_PERMISSION_PRIMARY_ACTION_SIZE =
    "run.nabla.gallerypicker.intent.extra.PRIMARY_ACTION_SIZE"
const val EXTRA_PERMISSION_PRIMARY_ACTION_ROUNDED_CORNER =
    "run.nabla.gallerypicker.intent.extra.PRIMARY_ACTION_ROUNDED_CORNER"
const val EXTRA_PERMISSION_PRIMARY_ACTION_BACKGROUND_COLOR =
    "run.nabla.gallerypicker.intent.extra.PRIMARY_ACTION_BACKGROUND_COLOR"
const val EXTRA_PERMISSION_SECONDARY_ACTION_TITLE =
    "run.nabla.gallerypicker.intent.extra.SECONDARY_ACTION_TITLE"
const val EXTRA_PERMISSION_SECONDARY_ACTION_COLOR =
    "run.nabla.gallerypicker.intent.extra.SECONDARY_ACTION_COLOR"
const val EXTRA_PERMISSION_SECONDARY_ACTION_SIZE =
    "run.nabla.gallerypicker.intent.extra.SECONDARY_ACTION_SIZE"
const val EXTRA_PERMISSION_SECONDARY_ACTION_ROUNDED_CORNER =
    "run.nabla.gallerypicker.intent.extra.SECONDARY_ACTION_ROUNDED_CORNER"
const val EXTRA_PERMISSION_SECONDARY_ACTION_BACKGROUND_COLOR =
    "run.nabla.gallerypicker.intent.extra.SECONDARY_ACTION_BACKGROUND_COLOR"
const val EXTRA_PERMISSION_SECONDARY_ACTION_BORDER_COLOR =
    "run.nabla.gallerypicker.intent.extra.SECONDARY_ACTION_BORDER_COLOR"


const val RESULT_URI = "run.nabla.gallerypicker.intent.result.URI"


open class GalleryContract : ActivityResultContract<GalleryRequest, Uri?>() {

    override fun createIntent(context: Context, input: GalleryRequest): Intent {
        val extrasToPut = mapOf(
            EXTRA_FONT_FAMILY to input.fontFamily,
            EXTRA_BACKGROUND_COLOR to input.backgroundColor,
            EXTRA_TITLE_COLOR to input.titleColor,
            EXTRA_TITLE to input.title,
            EXTRA_TITLE_SIZE to input.titleSize,
            EXTRA_HEADER_PADDING_VERTICAL to input.titlePaddingVertical,
            EXTRA_HEADER_PADDING_HORIZONTAL to input.titlePaddingHorizontal,
            EXTRA_SHOW_EXIT_ACTION to input.showExitAction,
            EXTRA_HORIZONTAL_PADDING to input.horizontalPadding,
            EXTRA_ITEM_ROUNDED_CORNER_SIZE to input.itemsRoundedCornerSize,
            EXTRA_GRID_COLUMNS to input.gridColumns,
            EXTRA_ITEM_MIN_HEIGHT to input.itemMinHeight,
            EXTRA_ITEM_MAX_HEIGHT to input.itemMaxHeight,
            EXTRA_PERMISSION_TITLE to input.permissionTitle,
            EXTRA_PERMISSION_TITLE_COLOR to input.permissionTitleColor,
            EXTRA_PERMISSION_TITLE_SIZE to input.permissionTitleSize,
            EXTRA_PERMISSION_BODY to input.permissionBody,
            EXTRA_PERMISSION_BODY_SIZE to input.permissionBodySize,
            EXTRA_PERMISSION_BODY_COLOR to input.permissionBodyColor,
            EXTRA_PERMISSION_BACKGROUND_COLOR to input.permissionBackgroundColor,
            EXTRA_PERMISSION_IMAGE to input.permissionImage,
            EXTRA_PERMISSION_PRIMARY_ACTION_TITLE to input.permissionPrimaryActionTitle,
            EXTRA_PERMISSION_PRIMARY_ACTION_COLOR to input.permissionPrimaryActionColor,
            EXTRA_PERMISSION_PRIMARY_ACTION_SIZE to input.permissionPrimaryActionSize,
            EXTRA_PERMISSION_PRIMARY_ACTION_ROUNDED_CORNER to input.permissionPrimaryActionRoundedCorner,
            EXTRA_PERMISSION_PRIMARY_ACTION_BACKGROUND_COLOR to input.permissionPrimaryActionBackgroundColor,
            EXTRA_PERMISSION_SECONDARY_ACTION_TITLE to input.permissionSecondaryActionTitle,
            EXTRA_PERMISSION_SECONDARY_ACTION_COLOR to input.permissionSecondaryActionColor,
            EXTRA_PERMISSION_SECONDARY_ACTION_SIZE to input.permissionSecondaryActionSize,
            EXTRA_PERMISSION_SECONDARY_ACTION_ROUNDED_CORNER to input.permissionSecondaryActionRoundedCorner,
            EXTRA_PERMISSION_SECONDARY_ACTION_BACKGROUND_COLOR to input.permissionSecondaryActionBackgroundColor,
            EXTRA_PERMISSION_SECONDARY_ACTION_BORDER_COLOR to input.permissionSecondaryActionBorderColor
        )

        return Intent(context, GalleryPickerActivity::class.java).apply {
            for ((extraKey, extraValue) in extrasToPut) {
                putExtra(extraKey, extraValue)
            }
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
        return intent.takeIf { resultCode == Activity.RESULT_OK }?.run {
            intent?.getStringExtra(RESULT_URI)?.let {
                Uri.parse(it)
            }
        }
    }
}

fun GalleryRequest.updateFromBundle(bundle: Bundle?) {
    bundle?.let {
        fontFamily = it.getInt(EXTRA_FONT_FAMILY, fontFamily)
        backgroundColor = it.getLong(EXTRA_BACKGROUND_COLOR, backgroundColor)
        titleColor = it.getLong(EXTRA_TITLE_COLOR, titleColor)
        title = it.getString(EXTRA_TITLE, title)
        titleSize = it.getInt(EXTRA_TITLE_SIZE, titleSize)
        showExitAction = it.getBoolean(EXTRA_SHOW_EXIT_ACTION, showExitAction)
        titlePaddingVertical = it.getInt(EXTRA_HEADER_PADDING_VERTICAL, titlePaddingVertical)
        titlePaddingHorizontal =
            it.getInt(EXTRA_HEADER_PADDING_HORIZONTAL, titlePaddingHorizontal)
        horizontalPadding = it.getInt(EXTRA_HORIZONTAL_PADDING, horizontalPadding)
        itemsRoundedCornerSize =
            it.getInt(EXTRA_ITEM_ROUNDED_CORNER_SIZE, itemsRoundedCornerSize)
        gridColumns = it.getInt(EXTRA_GRID_COLUMNS, gridColumns)
        itemMinHeight = it.getInt(EXTRA_ITEM_MIN_HEIGHT, itemMinHeight)
        itemMaxHeight = it.getInt(EXTRA_ITEM_MAX_HEIGHT, itemMaxHeight)
        permissionTitle = it.getString(EXTRA_PERMISSION_TITLE, permissionTitle)
        permissionTitleColor = it.getLong(EXTRA_PERMISSION_TITLE_COLOR, permissionTitleColor)
        permissionTitleSize = it.getInt(EXTRA_PERMISSION_TITLE_SIZE, permissionTitleSize)
        permissionBody = it.getString(EXTRA_PERMISSION_BODY, permissionBody)
        permissionBodySize = it.getInt(EXTRA_PERMISSION_BODY_SIZE, permissionBodySize)
        permissionBodyColor = it.getLong(EXTRA_PERMISSION_BODY_COLOR, permissionBodyColor)
        permissionBackgroundColor = it.getLong(
            EXTRA_PERMISSION_BACKGROUND_COLOR,
            permissionBackgroundColor
        )
        permissionImage = it.getInt(EXTRA_PERMISSION_IMAGE, permissionImage)
        permissionPrimaryActionTitle = it.getString(
            EXTRA_PERMISSION_PRIMARY_ACTION_TITLE,
            permissionPrimaryActionTitle
        )
        permissionPrimaryActionColor = it.getLong(
            EXTRA_PERMISSION_PRIMARY_ACTION_COLOR,
            permissionPrimaryActionColor
        )
        permissionPrimaryActionSize = it.getInt(
            EXTRA_PERMISSION_PRIMARY_ACTION_SIZE,
            permissionPrimaryActionSize
        )
        permissionPrimaryActionRoundedCorner = it.getInt(
            EXTRA_PERMISSION_PRIMARY_ACTION_ROUNDED_CORNER,
            permissionPrimaryActionRoundedCorner
        )
        permissionPrimaryActionBackgroundColor = it.getLong(
            EXTRA_PERMISSION_PRIMARY_ACTION_BACKGROUND_COLOR,
            permissionPrimaryActionBackgroundColor
        )
        permissionSecondaryActionTitle = it.getString(
            EXTRA_PERMISSION_SECONDARY_ACTION_TITLE,
            permissionSecondaryActionTitle
        )
        permissionSecondaryActionColor = it.getLong(
            EXTRA_PERMISSION_SECONDARY_ACTION_COLOR,
            permissionSecondaryActionColor
        )
        permissionSecondaryActionSize = it.getInt(
            EXTRA_PERMISSION_SECONDARY_ACTION_SIZE,
            permissionSecondaryActionSize
        )
        permissionSecondaryActionRoundedCorner = it.getInt(
            EXTRA_PERMISSION_SECONDARY_ACTION_ROUNDED_CORNER,
            permissionSecondaryActionRoundedCorner
        )
        permissionSecondaryActionBackgroundColor = it.getLong(
            EXTRA_PERMISSION_SECONDARY_ACTION_BACKGROUND_COLOR,
            permissionSecondaryActionBackgroundColor
        )
        permissionSecondaryActionBorderColor = it.getLong(
            EXTRA_PERMISSION_SECONDARY_ACTION_BORDER_COLOR,
            permissionSecondaryActionBorderColor
        )
    }
}
