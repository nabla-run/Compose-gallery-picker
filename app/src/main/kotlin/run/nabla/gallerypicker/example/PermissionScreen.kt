package run.nabla.gallerypicker.example

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import run.nabla.gallerypicker.components.PermissionRequestDialog
import run.nabla.gallerypicker.components.dialogColors

@Composable
fun PermissionScreen(
    modifier: Modifier = Modifier,
    onPermissionGranted: () -> Unit,
) {
    PermissionRequestDialog(
        modifier = modifier,
        onPermissionGranted = { onPermissionGranted() },
        colors = dialogColors(
            actionBackgroundColor = Color.Black,
            actionContentColor = Color.White
        )
    )
}