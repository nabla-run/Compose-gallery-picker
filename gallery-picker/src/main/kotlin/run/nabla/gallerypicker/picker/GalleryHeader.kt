package run.nabla.gallerypicker.picker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val DEFAULT_HEADER_TITLE = "PICK A PHOTO"
const val DEFAULT_HEADER_TITLE_SIZE = 25

@Composable
fun GalleryHeader(
    modifier: Modifier = Modifier,
    title: String = DEFAULT_HEADER_TITLE,
    titleSize: Int = DEFAULT_HEADER_TITLE_SIZE,
    titleColor: Long = Color.White.value.toLong(),
    actionIcon: ImageVector = Icons.Default.Close,
    onLeftActionClick: (() -> Unit)? = null
) {
    if (title.isEmpty()) return
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            color = Color(titleColor.toULong()),
            fontSize = titleSize.sp,
            fontWeight = FontWeight.Bold
        )
        onLeftActionClick?.let {
            Icon(
                modifier = Modifier.clickable {
                    onLeftActionClick()
                },
                imageVector = actionIcon,
                contentDescription = "",
                tint = Color(titleColor.toULong())
            )
        }
    }
}