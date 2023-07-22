package run.nabla.gallerypicker.editor

import android.graphics.Bitmap
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.toSize
import run.nabla.gallerypicker.R
import run.nabla.gallerypicker.components.PhotoBox
import run.nabla.gallerypicker.components.PhotoState
import run.nabla.gallerypicker.components.rememberPhotoState
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
    bitmap: Bitmap?,
    footer: @Composable BoxScope.() -> Unit = {}
) {
    if (template != {} && templateState == null) {
        throw Exception("Template is not empty, but templateState is null.")
    }

    var size by remember { mutableStateOf(IntSize.Zero) }

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
                state = photoState
            ) {
                Image(
                    painter = bitmap?.toPainter()
                        ?: painterResource(id = R.drawable.ic_default_photo),
                    contentScale = ContentScale.Fit,
                    contentDescription = ""
                )
            }
            template()
            footer()
        }
    }
}
