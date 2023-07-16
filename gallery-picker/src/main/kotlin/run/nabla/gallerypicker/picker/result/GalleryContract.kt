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
const val EXTRA_SHOW_BACK_BUTTON = "run.nabla.gallerypicker.intent.extra.SHOW_BACK_BUTTON"

const val RESULT_URI = "run.nabla.gallerypicker.intent.result.URI"


open class GalleryContract : ActivityResultContract<GalleryRequest, Uri?>() {
    companion object {

    }

    override fun createIntent(context: Context, input: GalleryRequest): Intent {
        return Intent(context, GalleryPickerActivity::class.java).apply {
            putExtra(EXTRA_BACKGROUND_COLOR, input.backgroundColor)
            putExtra(EXTRA_TITLE_COLOR, input.titleColor)
            putExtra(EXTRA_TITLE, input.title)
            putExtra(EXTRA_TITLE_SIZE, input.titleSize)
            putExtra(EXTRA_SHOW_BACK_BUTTON, input.showBackButton)
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
            showBackButton = bundle?.getBoolean(EXTRA_SHOW_BACK_BUTTON) ?: showBackButton
        }
}
