package run.nabla.gallerypicker.example

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import run.nabla.gallerypicker.R
import run.nabla.gallerypicker.picker.result.GalleryContract
import run.nabla.gallerypicker.picker.result.GalleryRequest

@Composable
fun GalleryIntentScreen() {
    val pickPhotoLauncher = rememberLauncherForActivityResult(
        contract = GalleryContract(),
        onResult = { uri ->

        }
    )

    LaunchedEffect(Unit) {
        pickPhotoLauncher.launch(
            GalleryRequest.Builder()
                .setTitle("Pick one")
                .setFontFamily(R.font.open_sans)
                .setTitleSize(25)
                .setBackgroundColor(Color.White.value.toLong())
                .setTitleColor(Color.Black.value.toLong())
                .showExitAction(false)
                .setItemsRoundedCornerSize(5)
                .setGridColumns(3)
                .setPermissionTitle("Access to your photos")
                .setPermissionSecondaryActionTitle("Cancel")
                .build()
        )
    }
}