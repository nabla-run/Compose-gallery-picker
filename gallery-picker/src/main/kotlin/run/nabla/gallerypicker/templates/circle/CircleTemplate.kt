package run.nabla.gallerypicker.templates.circle

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize

@Composable
fun BoxScope.Circle(
    modifier: Modifier = Modifier,
    diameterRatio: Float
) {

    val size = remember { mutableStateOf(IntSize.Zero) }

    Canvas(
        modifier = modifier
            .matchParentSize()
            .onSizeChanged {
                size.value = it
            }
    ) {
        drawIntoCanvas {
            val circlePath = Path().apply {
                addOval(
                    Rect(
                        center,
                        (size.value.width * diameterRatio) / 2f
                    )
                )
            }
            clipPath(circlePath, clipOp = ClipOp.Difference) {
                drawRect(SolidColor(Color(0x80000000)))
            }
        }
    }
}
