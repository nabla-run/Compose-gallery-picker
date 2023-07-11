package run.nabla.gallerypicker.picker

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import run.nabla.gallerypicker.MediaPhoto

@Composable
fun GalleryPicker(
    modifier: Modifier = Modifier,
    primaryColor: Color = Color.Black,
    onImageSelected: (Uri) -> Unit
) {
    var permissionGranted by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val lazyGridState = rememberLazyGridState()

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

    LazyVerticalGrid(
        modifier = modifier.background(primaryColor),
        state = lazyGridState,
        columns = GridCells.Fixed(3)
    ) {
        items(
            count = photos.size,
        ) {
            val photo = photos[it]
            Image(
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = primaryColor,
                        shape = RectangleShape
                    )
                    .clickable {
                        onImageSelected(photo.uri)
                    },
                contentScale = ContentScale.Crop,
                painter = rememberAsyncImagePainter(
                    photo.uri
                ),
                contentDescription = null
            )
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

@Composable
fun rememberMediaPhotos(
    context: Context
): List<MediaPhoto> {
    val photos = remember { mutableStateListOf<MediaPhoto>() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(coroutineScope) {
        withContext(Dispatchers.IO) {
            val projection = arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME
            )

            val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"

            val query = context.contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                sortOrder
            )

            query?.use { cursor ->
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)

                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idColumn)
                    val name = cursor.getString(nameColumn)
                    val contentUri = ContentUris.withAppendedId(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        id
                    )

                    photos.add(
                        MediaPhoto(
                            name = name,
                            uri = contentUri
                        )
                    )
                }
            }
        }
    }
    return photos
}