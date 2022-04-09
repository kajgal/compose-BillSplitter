package com.example.jettip.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MeetingRoom
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.Money
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    enabled: Boolean,
    isSingleLine: Boolean,
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        modifier = modifier.padding(bottom = 10.dp,start = 10.dp, end = 10.dp),
        label = { Text(
            text = label,
            fontSize = 16.sp
        )},
        leadingIcon = { Icon(
            imageVector = Icons.Rounded.AttachMoney,
            contentDescription = "Money Icon")},
        singleLine = isSingleLine,
        textStyle = TextStyle(
            fontSize = 18.sp,
            color = MaterialTheme.colors.onBackground
        ),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = onAction,
        onValueChange = onValueChange
    )
}

@Composable
fun CardButton(
    modifier: Modifier = Modifier,
    elevation: Dp,
    imageVector: ImageVector,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .clickable {
                onClick()
            },
        elevation = elevation,
        shape = CircleShape
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "Button Image"
        )
    }
}