package run.nabla.gallerypicker.example

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import run.nabla.gallerypicker.components.PhotoState
import run.nabla.gallerypicker.components.rememberPhotoState
import run.nabla.gallerypicker.editor.ImageEditor
import run.nabla.gallerypicker.extensions.saveAsSquare
import run.nabla.gallerypicker.extensions.toScaledBitmap
import run.nabla.gallerypicker.templates.TemplateState
import run.nabla.gallerypicker.templates.rememberTemplateState
import run.nabla.gallerypicker.templates.square.Square
import run.nabla.gallerypicker.utils.getScreenSize

@Composable
fun EditorScreen(
    fileUri: Uri,
    onBackClick: () -> Unit
) {
    val photoState: PhotoState = rememberPhotoState()
    val templateState: TemplateState = rememberTemplateState()

    val context = LocalContext.current
    val screenSize = getScreenSize()

    var bitmap: Bitmap? by remember { mutableStateOf(null) }
    LaunchedEffect(fileUri) {
        withContext(Dispatchers.IO) {
            bitmap = fileUri.toScaledBitmap(context, screenSize)
        }
    }

    ImageEditor(
        photoState = photoState,
        bitmap = bitmap,
        templateState = templateState,
        template = {
            Square(
                diameterRatio = templateState.sizeRatio
            )
        },
        footer = {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .background(Color.Black)
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(horizontal = 25.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF37373b),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(
                        horizontal = 0.dp,
                        vertical = 10.dp
                    ),
                    onClick = onBackClick
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                }
                Text(
                    modifier = Modifier
                        .weight(1f),
                    text = "Crop",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFB69554),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(
                        horizontal = 0.dp,
                        vertical = 10.dp
                    ),
                    onClick = {
                        bitmap?.saveAsSquare(
                            context = context,
                            scale = photoState.currentScale,
                            offset = photoState.calculateCurrentOffsetWithTemplateSize(),
                            templateSize = photoState.templateSize
                        )
                        onBackClick()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Done,
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    )
}