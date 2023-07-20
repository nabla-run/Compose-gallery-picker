package run.nabla.gallerypicker.templates.square

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
fun BoxScope.Square(
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
            val squarePath = Path().apply {
                val centerX = center.x
                val centerY = center.y
                val halfSize = (size.value.width * diameterRatio) / 2f

                val left = centerX - halfSize
                val top = centerY - halfSize
                val right = centerX + halfSize
                val bottom = centerY + halfSize

                val squareRect = Rect(left, top, right, bottom)
                addRect(squareRect)
            }
            clipPath(squarePath, clipOp = ClipOp.Difference) {
                drawRect(SolidColor(Color(0x80000000)))
            }
        }
    }
}
