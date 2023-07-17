package run.nabla.gallerypicker.picker

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import run.nabla.gallerypicker.R
import run.nabla.gallerypicker.permission.rememberRequestPermissionState
import run.nabla.gallerypicker.picker.result.GalleryRequest
import run.nabla.gallerypicker.picker.result.RESULT_URI
import run.nabla.gallerypicker.picker.result.updateFromBundle

class GalleryPickerActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        }
        onBackPressedDispatcher.addCallback(
            this,
            callback
        )

        val request = GalleryRequest().apply {
            this.updateFromBundle(intent.extras)
        }

        setContent {
            val onImageSelected: (Uri) -> Unit = {
                val resultIntent = Intent()
                resultIntent.putExtra(RESULT_URI, it.toString())
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
            GalleryPicker(
                onImageSelected = onImageSelected,
                backgroundColor = Color(request.backgroundColor.toULong()),
                state = rememberGalleryPickerState(
                    horizontalPadding = request.horizontalPadding,
                    roundedCornerSize = request.itemsRoundedCornerSize,
                    gridColumns = request.gridColumns,
                    itemMinHeight = request.itemMinHeight,
                    itemMaxHeight = request.itemMaxHeight,
                ),
                permissionState = rememberRequestPermissionState(
                    title = if (request.permissionTitle.isEmpty()) getString(R.string.storage_permission_request_title) else request.permissionTitle,
                    titleColor = request.permissionTitleColor,
                    titleSize = request.permissionTitleSize,
                    body = if (request.permissionBody.isEmpty()) getString(R.string.storage_permission_request_body) else request.permissionBody,
                    bodyColor = request.permissionBodyColor,
                    bodySize = request.permissionBodySize,
                    backgroundColor = request.permissionBackgroundColor,
                    image = request.permissionImage,
                    primaryActionTitle = if (request.permissionPrimaryActionTitle.isEmpty()) getString(
                        R.string.storage_permission_request_confirm
                    ) else request.permissionPrimaryActionTitle,
                    primaryActionColor = request.permissionPrimaryActionColor,
                    primaryActionSize = request.permissionPrimaryActionSize,
                    primaryActionRoundedCorner = request.permissionPrimaryActionRoundedCorner,
                    primaryActionBackgroundColor = request.permissionPrimaryActionBackgroundColor,
                    secondaryActionTitle = request.permissionSecondaryActionTitle,
                    secondaryActionColor = request.permissionSecondaryActionColor,
                    secondaryActionSize = request.permissionSecondaryActionSize,
                    secondaryActionRoundedCorner = request.permissionSecondaryActionRoundedCorner,
                    secondaryActionBackgroundColor = request.permissionSecondaryActionBackgroundColor,
                    secondaryActionBorderColor = request.permissionSecondaryActionBorderColor,
                ),
                header = {
                    GalleryHeader(
                        title = request.title,
                        titleSize = request.titleSize,
                        titleColor = request.titleColor,
                        paddingVertical = request.titlePaddingVertical,
                        paddingHorizontal = request.titlePaddingHorizontal,
                        onLeftActionClick = if (request.showExitAction) {
                            { onBackPressedDispatcher.onBackPressed() }
                        } else {
                            null
                        }
                    )
                }
            )
        }
    }
}