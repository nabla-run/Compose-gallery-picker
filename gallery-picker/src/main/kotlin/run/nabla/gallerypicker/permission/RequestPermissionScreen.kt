package run.nabla.gallerypicker.permission

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState


@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun RequestPermissionScreen(
    modifier: Modifier = Modifier,
    state: RequestPermissionState = rememberRequestPermissionState(),
    permissionState: PermissionState,

    ) {
    Column(
        modifier = modifier
            .background(Color(state.backgroundColor.toULong()))
            .statusBarsPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(0.25f))
        Image(
            painter = painterResource(id = state.image),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.weight(0.10f))
        Text(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(0.75f),
            color = Color(state.titleColor.toULong()),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            text = state.title,
            fontSize = state.titleSize.sp
        )
        Text(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(0.75f),
            color = Color(state.bodyColor.toULong()),
            textAlign = TextAlign.Center,
            text = state.body,
            fontSize = state.bodySize.sp
        )
        Spacer(modifier = Modifier.weight(0.25f))
        if (!state.secondaryActionTitle.isEmpty()) {
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth(0.75f),
                shape = RoundedCornerShape(state.secondaryActionRoundedCorner.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Color(state.secondaryActionBackgroundColor.toULong()),
                ),
                border = BorderStroke(
                    1.dp,
                    Color(state.secondaryActionBorderColor.toULong())
                ),
                onClick = {

                }
            ) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    color = Color(state.secondaryActionColor.toULong()),
                    text = state.secondaryActionTitle,
                    fontSize = state.secondaryActionSize.sp
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
        }
        Button(
            modifier = Modifier
                .fillMaxWidth(0.75f),
            shape = RoundedCornerShape(state.primaryActionRoundedCorner.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(state.primaryActionBackgroundColor.toULong()),
            ),
            onClick = {
                permissionState.launchPermissionRequest()
            }
        ) {
            Text(
                modifier = Modifier.padding(5.dp),
                color = Color(state.primaryActionColor.toULong()),
                text = state.primaryActionTitle,
                fontSize = state.primaryActionSize.sp
            )
        }
        Spacer(modifier = Modifier.weight(0.25f))
    }
}
