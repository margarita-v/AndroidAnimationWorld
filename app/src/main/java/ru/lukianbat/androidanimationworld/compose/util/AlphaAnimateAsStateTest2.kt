package ru.lukianbat.androidanimationworld.compose.util

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.lukianbat.androidanimationworld.R

@Composable
fun AlphaAnimateAsStateTest2() {
    // TODO 2 Запуск анимации изменения alpha с помощью функции animateFloatAsState
    var imageEnabled by remember { mutableStateOf(false) }
    val alpha: Float by animateFloatAsState(
        targetValue = if (imageEnabled) 1f else 0f,
        animationSpec = tween(1500)
    )
    Image(
        painter = painterResource(R.drawable.ic_podlodka),
        contentDescription = null,
        modifier = Modifier
            .size(100F.dp, 100F.dp)
            .alpha(alpha)
    )

    Button(
        onClick = {
            imageEnabled = imageEnabled.not()
        },
        Modifier.offset(y = 300.dp)
    ) {
        Text("Change imageEnabled state")
    }
}