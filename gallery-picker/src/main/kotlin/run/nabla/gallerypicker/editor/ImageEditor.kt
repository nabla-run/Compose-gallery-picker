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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.toSize
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import run.nabla.gallerypicker.R
import run.nabla.gallerypicker.components.PhotoBox
import run.nabla.gallerypicker.components.PhotoState
import run.nabla.gallerypicker.components.rememberPhotoState
import run.nabla.gallerypicker.extensions.toPainter
import run.nabla.gallerypicker.extensions.toScaledBitmap
import run.nabla.gallerypicker.templates.TemplateState
import run.nabla.gallerypicker.templates.getTemplateBounce
import run.nabla.gallerypicker.utils.getScreenSize

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
    if (template != {} && templateState == null) {
        throw Exception("Template is not empty, but templateState is null.")
    }

    val context = LocalContext.current
    val screenSize = getScreenSize()
    var bitmap: Bitmap? by remember { mutableStateOf(null) }
    var size by remember { mutableStateOf(IntSize.Zero) }
    LaunchedEffect(photoURI) {
        withContext(Dispatchers.IO) {
            bitmap = photoURI.toScaledBitmap(context, screenSize)
        }
    }

    LaunchedEffect(size, bitmap) {
        bitmap?.let { picture ->
            templateState?.let {
                photoState.containerBounds =
                    templateState.getTemplateBounce(
                        size.toSize(),
                        picture.width,
                        picture.height
                    )
                photoState.templateSize = Size(
                    width = size.width * templateState.sizeRatio,
                    height = size.width * templateState.sizeRatio
                )
            }
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
                    painter = bitmap?.toPainter()
                        ?: painterResource(id = R.drawable.ic_default_photo),
                    contentScale = ContentScale.Fit,
                    contentDescription = ""
                )
            }
            template()
            footer(
                primaryClick = {
                    bitmap?.let {
                        onDoneClick(
                            it,
                            imageScale,
                            imageOffset,
                            photoState.templateSize
                        )
                    }
                }
            )
        }
    }
}