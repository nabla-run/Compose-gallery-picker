package run.nabla.gallerypicker.editor

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.toSize
import run.nabla.gallerypicker.components.PhotoBox
import run.nabla.gallerypicker.components.PhotoState
import run.nabla.gallerypicker.components.rememberPhotoState
import run.nabla.gallerypicker.extensions.toBitmap
import run.nabla.gallerypicker.extensions.toPainter
import run.nabla.gallerypicker.templates.TemplateState
import run.nabla.gallerypicker.templates.getTemplateBounce

@Composable
fun ImageEditor(
    modifier: Modifier = Modifier,
    photoState: PhotoState = rememberPhotoState(),
    template: @Composable BoxScope.() -> Unit = {},
    templateState: TemplateState? = null,
    primaryColor: Color = Color.Black,
    photoURI: Uri,
    onDoneClick: (
        bitmap: Bitmap,
        scale: Float,
        offset: Offset,
        templateSize: Size
    ) -> Unit,
    footer: @Composable BoxScope.(
        primaryClick: () -> Unit,
    ) -> Unit,
) {
    val bitmap = photoURI.toBitmap(LocalContext.current)
    var size by remember { mutableStateOf(IntSize.Zero) }
    LaunchedEffect(size) {
        templateState?.let {
            photoState.containerBounds =
                templateState.getTemplateBounce(
                    size.toSize(),
                    bitmap.width,
                    bitmap.height
                )
            photoState.templateSize = Size(
                width = size.width * templateState.sizeRatio,
                height = size.width * templateState.sizeRatio
            )
        }
    }

    var imageOffset = Offset(0f, 0f)
    var imageScale = 1f

    Column(
        modifier = modifier
            .fillMaxSize()
            .onSizeChanged {
                size = it
            }
            .background(primaryColor)
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            PhotoBox(
                modifier = Modifier,
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
                    contentScale = ContentScale.Fit,
                    contentDescription = ""
                )
            }
            template()
            footer(
                primaryClick = {
                    onDoneClick(
                        bitmap,
                        imageScale,
                        imageOffset,
                        photoState.templateSize
                    )
                }
            )
        }
    }
}