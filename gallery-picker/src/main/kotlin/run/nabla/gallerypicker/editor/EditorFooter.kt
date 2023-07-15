package run.nabla.gallerypicker.editor

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun BoxScope.EditorFooter(
    onPrimaryActionClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 25.dp)
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = onPrimaryActionClick
        ) {
            Text(text = "OK")
        }
    }
}