package run.nabla.gallerypicker.example

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import run.nabla.gallerypicker.components.PhotoState
import run.nabla.gallerypicker.components.rememberPhotoState
import run.nabla.gallerypicker.editor.EditorFooter
import run.nabla.gallerypicker.editor.ImageEditor
import run.nabla.gallerypicker.picker.GalleryPicker
import run.nabla.gallerypicker.templates.TemplateState
import run.nabla.gallerypicker.templates.circle.Circle
import run.nabla.gallerypicker.templates.rememberTemplateState
import run.nabla.gallerypicker.utils.saveAsOval

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "gallery-picker"
            ) {
                galleryPicker(
                    onImageSelected = {
                        navController.navigate("image-editor/${Uri.encode(it.toString())}")
                    }
                )
                imageEditor(
                    onBackClick = navController::navigateUp
                )
            }
        }
    }
}

fun NavGraphBuilder.galleryPicker(
    onImageSelected: (Uri) -> Unit
) {
    composable(route = "gallery-picker") {
        GalleryPicker(
            onImageSelected = onImageSelected
        )
    }
}

fun NavGraphBuilder.imageEditor(
    onBackClick: () -> Unit
) {
    composable(
        route = "image-editor/{fileUri}",
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
                Circle(
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
                bitmap.saveAsOval(
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