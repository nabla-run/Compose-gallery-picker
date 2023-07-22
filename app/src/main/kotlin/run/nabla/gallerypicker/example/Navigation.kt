package run.nabla.gallerypicker.example

import android.net.Uri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import run.nabla.gallerypicker.picker.GalleryPicker

const val GALLERY_SCREEN = "gallery-screen"
const val IMAGE_EDITOR_SCREEN = "image-editor"
const val GALLERY_INTENT_SCREEN = "gallery-intent-screen"

fun NavGraphBuilder.galleryPicker(
    onImageSelected: (Uri) -> Unit
) {
    composable(route = GALLERY_SCREEN) {
        GalleryPicker(
            onImageSelected = onImageSelected
        )
    }
}

fun NavGraphBuilder.imageEditor(
    onBackClick: () -> Unit
) {
    composable(
        route = "$IMAGE_EDITOR_SCREEN/{fileUri}",
        arguments = listOf(navArgument("fileUri") { type = NavType.StringType })
    ) {
        val fileUri = Uri.parse(Uri.decode(it.arguments?.getString("fileUri")))
        EditorScreen(
            fileUri = fileUri,
            onBackClick = onBackClick
        )
    }
}

fun NavGraphBuilder.galleryIntentScreen(
) {
    composable(
        route = GALLERY_INTENT_SCREEN
    ) {
        GalleryIntentScreen()
    }
}