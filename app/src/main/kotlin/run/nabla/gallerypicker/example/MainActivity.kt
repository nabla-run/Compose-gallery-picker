package run.nabla.gallerypicker.example

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Size
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
import run.nabla.gallerypicker.editor.ImageEditor
import run.nabla.gallerypicker.picker.GalleryPicker
import run.nabla.gallerypicker.templates.TemplateState
import run.nabla.gallerypicker.templates.circle.Circle
import run.nabla.gallerypicker.templates.rememberTemplateState

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
                imageEditor()
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

fun NavGraphBuilder.imageEditor() {
    composable(
        route = "image-editor/{fileUri}",
        arguments = listOf(navArgument("fileUri") { type = NavType.StringType })
    ) {
        val fileUri = Uri.parse(Uri.decode(it.arguments?.getString("fileUri")))
        val photoState: PhotoState = rememberPhotoState()
        val templateState: TemplateState = rememberTemplateState()

        ImageEditor(
            photoState = photoState,
            photoURI = fileUri,
            templateState = templateState,
            template = {
                Circle(
                    diameterRatio = templateState.sizeRatio
                )
            },
            onImageEdited = {

            },
        )
    }
}