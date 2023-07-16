package run.nabla.gallerypicker.example

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import run.nabla.gallerypicker.picker.result.GalleryContract
import run.nabla.gallerypicker.picker.result.GalleryRequest

@Composable
fun ExampleScreen() {
    val pickPhotoLauncher = rememberLauncherForActivityResult(
        contract = GalleryContract(),
        onResult = { uri ->

        }
    )

    SideEffect {
        pickPhotoLauncher.launch(
            GalleryRequest.Builder()
                .setTitle("Pick one")
                .setTitleSize(25)
                .setBackgroundColor(Color.White.value.toLong())
                .setTitleColor(Color.Black.value.toLong())
                .setShowBackButton(false)
                .build()
        )
    }
}