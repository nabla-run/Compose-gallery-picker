package run.nabla.gallerypicker.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.net.Uri
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.core.graphics.applyCanvas
import java.io.File
import java.io.FileOutputStream

fun Bitmap.toPainter() = BitmapPainter(this.asImageBitmap())

fun Bitmap.toScaledBitmap(
    scale: Float,
    radius: Float
): Bitmap {
    val scaledBitmapWidth = (this.width * scale).toInt()
    val scaledBitmapHeight = (this.height * scale).toInt()
    return Bitmap.createScaledBitmap(
        this,
        scaledBitmapWidth,
        scaledBitmapHeight,
        false
    )
}

fun Bitmap.toSquare(
    scale: Float,
    offset: Offset,
    radius: Float
): Bitmap {
    val softwareBitmap = this.copy(Bitmap.Config.ARGB_8888, true)
    val scaledBitmap = softwareBitmap.toScaledBitmap(scale, radius)
    val output = androidx.core.graphics.createBitmap(
        (radius * 2).toInt(),
        (radius * 2).toInt(),
        Bitmap.Config.ARGB_8888
    )
    val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)
    output.applyCanvas {
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

fun Bitmap.toOval(
    scale: Float,
    offset: Offset,
    radius: Float
): Bitmap {
    val softwareBitmap = this.copy(Bitmap.Config.ARGB_8888, true)
    val scaledBitmap = softwareBitmap.toScaledBitmap(scale, radius)
    val output = androidx.core.graphics.createBitmap(
        (radius * 2).toInt(),
        (radius * 2).toInt(),
        Bitmap.Config.ARGB_8888
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
    fileName: String = "oval_image.png",
    quality: Int = 100,
    compressFormat: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG,
    context: Context,
    scale: Float,
    offset: Offset,
    templateSize: Size
): Uri {
    val bitmapCrop = this.toOval(
        scale = scale,
        offset = offset,
        radius = templateSize.width / 2
    )
    val file = File(context.cacheDir, fileName)
    FileOutputStream(file).use { out ->
        bitmapCrop.compress(compressFormat, quality, out)
    }
    return Uri.parse(file.toString())
}

fun Bitmap.saveAsSquare(
    fileName: String = "square_image.jpg",
    quality: Int = 100,
    compressFormat: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
    context: Context,
    scale: Float,
    offset: Offset,
    templateSize: Size
): Uri {
    val bitmapCrop = this.toSquare(
        scale = scale,
        offset = offset,
        radius = templateSize.width / 2
    )
    val file = File(context.cacheDir, fileName)
    FileOutputStream(file).use { out ->
        bitmapCrop.compress(compressFormat, quality, out)
    }
    return Uri.parse(file.toString())
}
