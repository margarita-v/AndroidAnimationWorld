package ru.lukianbat.androidanimationworld.compose.util

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.lukianbat.androidanimationworld.R

@Composable
fun AnimateContentSizeTest20() {
    // TODO 20 Анимация изменения размера контента с помощью animateContentSize
    var podlodkaSize by remember { mutableStateOf(100) }
    Image(
        painter = painterResource(R.drawable.ic_podlodka),
        contentDescription = null,
        modifier = Modifier
            .size(podlodkaSize.dp, podlodkaSize.dp)
            .clickable { podlodkaSize += 50 }
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioHighBouncy
                )
            ),
    )
}