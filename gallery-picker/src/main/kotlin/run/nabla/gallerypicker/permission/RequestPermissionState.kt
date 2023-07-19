package run.nabla.gallerypicker.permission

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import run.nabla.gallerypicker.R


const val DEFAULT_PERMISSION_TITLE_SIZE = 20
const val DEFAULT_PERMISSION_BODY_SIZE = 15
const val DEFAULT_PERMISSION_PRIMARY_ACTION_SIZE = 15
const val DEFAULT_PERMISSION_PRIMARY_ACTION_ROUNDED_CORNER = 5
const val DEFAULT_PERMISSION_SECONDARY_ACTION_SIZE = 15
const val DEFAULT_PERMISSION_SECONDARY_ACTION_ROUNDED_CORNER = 5

@Composable
fun rememberRequestPermissionState(
    title: String = stringResource(id = R.string.storage_permission_request_title),
    titleColor: Long = Color.Black.value.toLong(),
    titleSize: Int = DEFAULT_PERMISSION_TITLE_SIZE,
    body: String = stringResource(id = R.string.storage_permission_request_body),
    bodyColor: Long = Color.Gray.value.toLong(),
    bodySize: Int = DEFAULT_PERMISSION_BODY_SIZE,
    backgroundColor: Long = Color.White.value.toLong(),
    image: Int = R.drawable.ic_permission_unlock,
    primaryActionTitle: String = stringResource(id = R.string.storage_permission_request_confirm),
    primaryActionColor: Long = Color.White.value.toLong(),
    primaryActionSize: Int = DEFAULT_PERMISSION_PRIMARY_ACTION_SIZE,
    primaryActionRoundedCorner: Int = DEFAULT_PERMISSION_PRIMARY_ACTION_ROUNDED_CORNER,
    primaryActionBackgroundColor: Long = Color.Black.value.toLong(),
    secondaryActionTitle: String = stringResource(id = R.string.storage_permission_request_cancel),
    secondaryActionColor: Long = Color.Black.value.toLong(),
    secondaryActionSize: Int = DEFAULT_PERMISSION_SECONDARY_ACTION_SIZE,
    secondaryActionRoundedCorner: Int = DEFAULT_PERMISSION_SECONDARY_ACTION_ROUNDED_CORNER,
    secondaryActionBackgroundColor: Long = Color.White.value.toLong(),
    secondaryActionBorderColor: Long = Color.Black.value.toLong(),
): RequestPermissionState = rememberSaveable(saver = RequestPermissionState.Saver) {
    RequestPermissionState(
        title = title,
        titleColor = titleColor,
        titleSize = titleSize,
        body = body,
        bodyColor = bodyColor,
        bodySize = bodySize,
        backgroundColor = backgroundColor,
        image = image,
        primaryActionTitle = primaryActionTitle,
        primaryActionColor = primaryActionColor,
        primaryActionSize = primaryActionSize,
        primaryActionRoundedCorner = primaryActionRoundedCorner,
        primaryActionBackgroundColor = primaryActionBackgroundColor,
        secondaryActionTitle = secondaryActionTitle,
        secondaryActionColor = secondaryActionColor,
        secondaryActionSize = secondaryActionSize,
        secondaryActionRoundedCorner = secondaryActionRoundedCorner,
        secondaryActionBackgroundColor = secondaryActionBackgroundColor,
        secondaryActionBorderColor = secondaryActionBorderColor,
    )
}

@Stable
class RequestPermissionState(
    val title: String,
    val titleColor: Long,
    val titleSize: Int,
    val body: String,
    val bodyColor: Long,
    val bodySize: Int,
    val backgroundColor: Long,
    val image: Int,
    val primaryActionTitle: String,
    val primaryActionColor: Long,
    val primaryActionSize: Int,
    val primaryActionRoundedCorner: Int,
    val primaryActionBackgroundColor: Long,
    val secondaryActionTitle: String,
    val secondaryActionColor: Long,
    val secondaryActionSize: Int,
    val secondaryActionRoundedCorner: Int,
    val secondaryActionBackgroundColor: Long,
    val secondaryActionBorderColor: Long,
) {
    internal companion object {
        /**
         * The default [Saver] implementation for [RequestPermissionState].
         */
        val Saver: Saver<RequestPermissionState, *> = listSaver(
            save = {
                listOf<Any>(
                    it.title,
                    it.titleColor,
                    it.titleSize,
                    it.body,
                    it.bodyColor,
                    it.bodySize,
                    it.backgroundColor,
                    it.image,
                    it.primaryActionTitle,
                    it.primaryActionColor,
                    it.primaryActionSize,
                    it.primaryActionRoundedCorner,
                    it.primaryActionBackgroundColor,
                    it.secondaryActionTitle,
                    it.secondaryActionColor,
                    it.secondaryActionSize,
                    it.secondaryActionRoundedCorner,
                    it.secondaryActionBackgroundColor,
                    it.secondaryActionBorderColor,
                )
            },
            restore = {
                RequestPermissionState(
                    title = it[0] as String,
                    titleColor = it[1] as Long,
                    titleSize = it[2] as Int,
                    body = it[3] as String,
                    bodyColor = it[4] as Long,
                    bodySize = it[5] as Int,
                    backgroundColor = it[6] as Long,
                    image = it[7] as Int,
                    primaryActionTitle = it[8] as String,
                    primaryActionColor = it[9] as Long,
                    primaryActionSize = it[10] as Int,
                    primaryActionRoundedCorner = it[11] as Int,
                    primaryActionBackgroundColor = it[12] as Long,
                    secondaryActionTitle = it[13] as String,
                    secondaryActionColor = it[14] as Long,
                    secondaryActionSize = it[15] as Int,
                    secondaryActionRoundedCorner = it[16] as Int,
                    secondaryActionBackgroundColor = it[17] as Long,
                    secondaryActionBorderColor = it[18] as Long,
                )
            }
        )
    }
}
