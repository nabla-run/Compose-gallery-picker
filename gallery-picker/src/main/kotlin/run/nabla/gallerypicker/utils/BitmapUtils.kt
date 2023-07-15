package run.nabla.gallerypicker.utils

import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import androidx.compose.ui.geometry.Offset
import androidx.core.graphics.applyCanvas

fun Bitmap.createCircle(
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
