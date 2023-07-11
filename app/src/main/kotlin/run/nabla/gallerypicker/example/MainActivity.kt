package run.nabla.gallerypicker.example

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import run.nabla.gallerypicker.editor.ImageEditor
import run.nabla.gallerypicker.picker.GalleryPicker

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
        ImageEditor(
            photoURI = fileUri,
            onImageEdited = {

            }
        )
    }
}