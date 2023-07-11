package run.nabla.gallerypicker.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter


fun Uri.toBitmap(
    context: Context
) =
    if (Build.VERSION.SDK_INT < 28) {
        MediaStore.Images.Media.getBitmap(
            context.contentResolver,
            this
        )
    } else {
        ImageDecoder.decodeBitmap(
            ImageDecoder.createSource(context.contentResolver, this)
        )
    }

fun Bitmap.toPainter() = BitmapPainter(this.asImageBitmap())
