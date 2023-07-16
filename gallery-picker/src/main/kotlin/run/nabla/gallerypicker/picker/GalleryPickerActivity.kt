package run.nabla.gallerypicker.picker

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color
import run.nabla.gallerypicker.picker.result.GalleryContract
import run.nabla.gallerypicker.picker.result.RESULT_URI

class GalleryPickerActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        val request = GalleryContract().fromBundle(intent.extras)

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
                header = {
                    GalleryHeader(
                        title = request.title,
                        titleSize = request.titleSize,
                        titleColor = request.titleColor,
                        onLeftActionClick = if (request.showBackButton) {
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