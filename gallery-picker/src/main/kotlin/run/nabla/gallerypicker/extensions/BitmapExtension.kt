package run.nabla.gallerypicker.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.core.graphics.applyCanvas
import java.io.File
import java.io.FileOutputStream

fun Bitmap.toPainter() = BitmapPainter(this.asImageBitmap())

fun Bitmap.createOval(
    scale: Float,
    offset: Offset,
    radius: Float
): Bitmap {
    val softwareBitmap = this.copy(Bitmap.Config.ARGB_8888, true)
    val output = androidx.core.graphics.createBitmap(
        (radius * 2).toInt(),
        (radius * 2).toInt(),
        Bitmap.Config.ARGB_8888
    )

    val scaledBitmapWidth = (softwareBitmap.width * scale).toInt()
    val scaledBitmapHeight = (softwareBitmap.height * scale).toInt()
    val scaledBitmap = Bitmap.createScaledBitmap(
        softwareBitmap,
        scaledBitmapWidth,
        scaledBitmapHeight,
        false
    )

    val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)
    output.applyCanvas {
        drawCircle(
            radius,
            radius,
            radius,
            paint
        )
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        drawBitmap(
            scaledBitmap,
            offset.x,
            offset.y,
            paint
        )
    }
    scaledBitmap.recycle()
    softwareBitmap.recycle()
    return output
}


fun Bitmap.saveAsOval(
    fileName: String = "oval_image.jpg",
    quality: Int = 100,
    compressFormat: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
    context: Context,
    scale: Float,
    offset: Offset,
    templateSize: Size
) {
    val bitmapCrop = this.createOval(
        scale = scale,
        offset = offset,
        radius = templateSize.width / 2
    )
    val file = File(context.cacheDir, fileName)
    FileOutputStream(file).use { out ->
        bitmapCrop.compress(compressFormat, quality, out)
    }
}
