package run.nabla.gallerypicker.components

import android.Manifest
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import run.nabla.gallerypicker.R

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionRequestDialog(
    modifier: Modifier = Modifier,
    title: String = "",
    body: String = stringResource(id = R.string.camera_permission_required),
    bodyRationale: String = stringResource(id = R.string.camera_permission_rationale),
    actionText: String = stringResource(id = R.string.permission_request),
    colors: DialogColors = dialogColors(),
    onPermissionGranted: () -> Unit
) {
    val storagePermissionState = rememberPermissionState(
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    val description: String = if (storagePermissionState.status.shouldShowRationale) {
        bodyRationale
    } else {
        body
    }

    if (!storagePermissionState.status.isGranted) {
        AlertDialog(
            modifier = modifier,
            backgroundColor = colors.backgroundColor,
            contentColor = colors.contentColor,
            shape = RoundedCornerShape(10.dp),
            title = { Text(text = title) },
            text = { Text(text = description) },
            buttons = {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    onClick = {
                        storagePermissionState.launchPermissionRequest()
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colors.actionBackgroundColor
                    )
                ) {
                    Text(
                        color = colors.actionContentColor,
                        text = actionText
                    )
                }
            },
            onDismissRequest = { }
        )
    } else {
        onPermissionGranted()
    }
}

@Composable
fun dialogColors(
    backgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    actionBackgroundColor: Color = MaterialTheme.colors.primary,
    actionContentColor: Color = contentColorFor(actionBackgroundColor),
): DialogColors = DialogColors(
    backgroundColor = backgroundColor,
    contentColor = contentColor,
    actionBackgroundColor = actionBackgroundColor,
    actionContentColor = actionContentColor
)

@Immutable
class DialogColors(
    val backgroundColor: Color,
    val contentColor: Color,
    val actionBackgroundColor: Color,
    val actionContentColor: Color
)