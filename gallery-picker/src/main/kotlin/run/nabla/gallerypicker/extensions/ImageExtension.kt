package run.nabla.gallerypicker.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.ui.geometry.Size

fun Uri.toScaledBitmap(context: Context, screenSize: Size): Bitmap = try {
    val bitmap = if (Build.VERSION.SDK_INT < 28) {
        MediaStore.Images.Media.getBitmap(context.contentResolver, this)
    } else {
        val source = ImageDecoder.createSource(context.contentResolver, this)
        ImageDecoder.decodeBitmap(source)
    }

    val scaleHeight = bitmap.height * screenSize.width / bitmap.width
    val scaleWidth = screenSize.width

    Bitmap.createScaledBitmap(bitmap, scaleWidth.toInt(), scaleHeight.toInt(), false)
} catch (e: Exception) {
    e.printStackTrace()
    Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8)
}