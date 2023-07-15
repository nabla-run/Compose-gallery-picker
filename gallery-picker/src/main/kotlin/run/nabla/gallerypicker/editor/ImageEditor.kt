package run.nabla.gallerypicker.editor

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.toSize
import java.io.File
import java.io.FileOutputStream
import run.nabla.gallerypicker.components.PhotoBox
import run.nabla.gallerypicker.components.PhotoState
import run.nabla.gallerypicker.components.rememberPhotoState
import run.nabla.gallerypicker.extensions.toBitmap
import run.nabla.gallerypicker.extensions.toPainter
import run.nabla.gallerypicker.templates.TemplateState
import run.nabla.gallerypicker.templates.getContainerBounce
import run.nabla.gallerypicker.utils.createCircle

@Composable
fun ImageEditor(
    modifier: Modifier = Modifier,
    photoState: PhotoState = rememberPhotoState(),
    template: @Composable BoxScope.() -> Unit = {},
    templateState: TemplateState? = null,
    primaryColor: Color = Color.Black,
    photoURI: Uri,
    onImageEdited: (Uri) -> Unit,
) {
    var size by remember { mutableStateOf(IntSize.Zero) }
    LaunchedEffect(size) {
        templateState?.let {
            photoState.containerBounds = templateState.getContainerBounce(size.toSize())
            photoState.templateSize = Size(
                width = size.width * templateState.sizeRatio,
                height = size.width * templateState.sizeRatio
            )
        }
    }
    val bitmap = photoURI.toBitmap(LocalContext.current)
    var imageOffset by remember { mutableStateOf(Offset(0f, 0f)) }
    var imageScale by remember { mutableFloatStateOf(1f) }
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .onSizeChanged {
                size = it
            }
            .background(primaryColor)
    ) {
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            PhotoBox(
                state = photoState,
                onOffsetChange = {
                    imageOffset = it
                },
                onScaleChange = {
                    imageScale = it
                }
            ) {
                Image(
                    painter = bitmap.toPainter(),
                    contentDescription = ""
                )
            }
            template()
            Button(
                modifier = Modifier.statusBarsPadding(),
                onClick = {
                    createBitmap(
                        context = context,
                        bitmap = bitmap,
                        scale = imageScale,
                        offset = imageOffset,
                        templateSize = photoState.templateSize
                    )
                }) {
                Text(text = "SAVE")
            }
        }
    }
}

fun createBitmap(
    context: Context,
    bitmap: Bitmap,
    scale: Float,
    offset: Offset,
    templateSize: Size
) {
    val bitmapCrop = bitmap.createCircle(
        scale = scale,
        offset = offset,
        radius = templateSize.width / 2
    )
    val file = File(context.cacheDir, "cropped_image_1.jpg")
    FileOutputStream(file).use { out ->
        bitmapCrop.compress(Bitmap.CompressFormat.JPEG, 100, out)
    }
}
