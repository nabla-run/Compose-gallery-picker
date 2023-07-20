package run.nabla.gallerypicker.example

import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import run.nabla.gallerypicker.components.PhotoState
import run.nabla.gallerypicker.components.rememberPhotoState
import run.nabla.gallerypicker.editor.EditorFooter
import run.nabla.gallerypicker.editor.ImageEditor
import run.nabla.gallerypicker.extensions.saveAsSquare
import run.nabla.gallerypicker.picker.GalleryPicker
import run.nabla.gallerypicker.templates.TemplateState
import run.nabla.gallerypicker.templates.rememberTemplateState
import run.nabla.gallerypicker.templates.square.Square

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
        val photoState: PhotoState = rememberPhotoState()
        val templateState: TemplateState = rememberTemplateState()

        val context = LocalContext.current

        ImageEditor(
            photoState = photoState,
            photoURI = fileUri,
            templateState = templateState,
            template = {
                Square(
                    diameterRatio = templateState.sizeRatio
                )
            },
            footer = { primaryClick ->
                EditorFooter(
                    onPrimaryActionClick = primaryClick,
                    onSecondaryActionClick = onBackClick
                )
            },
            onDoneClick = { bitmap, scale, offset, templateSize ->
                bitmap.saveAsSquare(
                    context = context,
                    scale = scale,
                    offset = offset,
                    templateSize = templateSize
                )
                onBackClick()
            },
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