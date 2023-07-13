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
    val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)
    val output = androidx.core.graphics.createBitmap(
        (radius * 2).toInt(),
        (radius * 2).toInt(),
        Bitmap.Config.ARGB_8888
    )
    output.applyCanvas {
        drawCircle(radius, radius, radius, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        drawBitmap(
            softwareBitmap,
            offset.x,
            offset.y,
            paint
        )
    }
    softwareBitmap.recycle()
    return output
}