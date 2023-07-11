package run.nabla.gallerypicker.editor

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.net.Uri
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.core.graphics.applyCanvas
import java.io.File
import java.io.FileOutputStream
import run.nabla.gallerypicker.components.PhotoBox
import run.nabla.gallerypicker.components.PhotoState
import run.nabla.gallerypicker.components.rememberPhotoState
import run.nabla.gallerypicker.extensions.toBitmap
import run.nabla.gallerypicker.extensions.toPainter

@Composable
fun ImageEditor(
    modifier: Modifier = Modifier,
    primaryColor: Color = Color.Black,
    photoURI: Uri,
    header: @Composable () -> Unit = {},
    onImageEdited: (Uri) -> Unit
) {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val widthInPx = with(density) { configuration.screenWidthDp.dp.toPx() }
    val heightInPx = with(density) { configuration.screenHeightDp.dp.toPx() }
    /*
    val painter =
        rememberAsyncImagePainter(model = Uri.parse("/data/user/0/run.nabla.gallerypicker/cache/cropped_image_1.jpg"))
    */
    val bitmap = photoURI.toBitmap(LocalContext.current)
    val contentSize = Size(
        width = widthInPx,
        height = heightInPx
    )
    val state: PhotoState = rememberPhotoState()
    state.setPhotoBounds(Size(contentSize.width, contentSize.height))

    Column(
        modifier = modifier
            .background(primaryColor)
    ) {
        Box {
            PhotoBox(
                state = state,
                onTap = {}
            ) {
                Image(
                    painter = bitmap.toPainter(),
                    contentDescription = ""
                )
            }
            Canvas(
                modifier = Modifier
                    .matchParentSize()
            ) {
                drawIntoCanvas {
                    val circlePath = Path().apply {
                        addOval(
                            Rect(
                                center,
                                (contentSize.minDimension / 3)
                            )
                        )
                    }
                    clipPath(circlePath, clipOp = ClipOp.Difference) {
                        drawRect(SolidColor(Color.Black.copy(alpha = 0.8f)))
                    }
                }
            }
        }
    }
}

fun createBitmap(context: Context, bitmap: Bitmap, scale: Float, offset: Offset) {
    val softwareBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)

    val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)

    val minSize = minOf(bitmap.width, bitmap.height)
    val radius = minSize / 2f
    val output = androidx.core.graphics.createBitmap(
        minSize,
        minSize,
        Bitmap.Config.ARGB_8888
    )
    output.applyCanvas {
        drawCircle(radius, radius, radius, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        drawBitmap(
            softwareBitmap,
            0f,
            -200f,
            paint
        )
    }

    val file = File(context.cacheDir, "cropped_image_1.jpg")
    FileOutputStream(file).use { out ->
        output.compress(Bitmap.CompressFormat.JPEG, 100, out)
    }
}
