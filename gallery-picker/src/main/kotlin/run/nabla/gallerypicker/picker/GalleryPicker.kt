package run.nabla.gallerypicker.picker

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GalleryPicker(
    modifier: Modifier = Modifier,
    state: GalleryPickerState = rememberGalleryPickerState(),
    primaryColor: Color = Color.Black,
    header: @Composable () -> Unit = { GalleryHeader(title = state.headerTitle) },
    onImageSelected: (Uri) -> Unit
) {
    var permissionGranted by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val lazyGridState = rememberLazyStaggeredGridState()

    CheckImageMediaPermission(
        onPermissionGranted = { permissionGranted = true }
    )

    val photos = if (permissionGranted) {
        rememberMediaPhotos(
            context = context
        )
    } else {
        emptyList()
    }

    LazyVerticalStaggeredGrid(
        modifier = modifier
            .background(primaryColor)
            .statusBarsPadding()
            .padding(horizontal = state.horizontalPadding.dp)
            .fillMaxWidth(),
        state = lazyGridState,
        columns = StaggeredGridCells.Fixed(3)
    ) {
        item(span = StaggeredGridItemSpan.FullLine) {
            header()
        }
        itemsIndexed(
            items = photos
        ) { _, photo ->
            val shape = RoundedCornerShape(state.roundedCornerSize)
            Card(
                shape = shape,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = shape)
                    .padding(2.dp),
                onClick = { onImageSelected(photo.uri) }
            ) {
                Image(
                    modifier = Modifier
                        .aspectRatio(photo.size.width / photo.size.height)
                        .heightIn(min = 150.dp, max = 250.dp)
                        .fillMaxWidth(),
                    painter = rememberAsyncImagePainter(
                        photo.uri
                    ),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun CheckImageMediaPermission(
    onPermissionGranted: () -> Unit
) {
    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    )
    val title = ""
    val text = if (cameraPermissionState.status.shouldShowRationale) {
        "The camera is important for this app. Please grant the permission."
    } else {
        "Camera permission required for this feature to be available. " +
                "Please grant the permission"
    }

    if (!cameraPermissionState.status.isGranted) {
        AlertDialog(
            title = { Text(text = title) },
            text = { Text(text = text) },
            buttons = {
                Button(
                    onClick = {
                        cameraPermissionState.launchPermissionRequest()
                    }
                ) {
                    Text("Request permission")
                }
            },
            onDismissRequest = { }
        )
    } else {
        onPermissionGranted()
    }
}