package run.nabla.gallerypicker.templates

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.geometry.Size

@Composable
fun rememberTemplateState(
    sizeRatio: Float = 0.8F, // 80%
): TemplateState = rememberSaveable(saver = TemplateState.Saver) {
    TemplateState(
        sizeRatio = sizeRatio
    )
}

@Stable
class TemplateState(
    val sizeRatio: Float,
) {
    internal companion object {
        /**
         * The default [Saver] implementation for [TemplateState].
         */
        val Saver: Saver<TemplateState, *> = listSaver(
            save = {
                listOf<Any>(
                    it.sizeRatio,
                )
            },
            restore = {
                TemplateState(
                    sizeRatio = it[1] as Float,
                )
            }
        )
    }
}

fun TemplateState.getContainerBounce(container: Size): Size =
    Size(
        width = container.minDimension - ((container.minDimension * sizeRatio)),
        height = container.maxDimension - ((container.minDimension * sizeRatio))
    )