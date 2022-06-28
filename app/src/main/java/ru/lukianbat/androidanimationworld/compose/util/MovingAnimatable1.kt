package ru.lukianbat.androidanimationworld.compose.util

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.lukianbat.androidanimationworld.R

@Composable
fun MovingAnimatable1() {
    // TODO 1 Запуск анимации перемещения с помощью объекта Animatable
    val startValue = 0F
    val endValue = 300F
    val yOffsetAnimatable = remember {
        Animatable(
            startValue,
            Float.VectorConverter
        )
    }

    Image(
        painter = painterResource(R.drawable.ic_podlodka),
        contentDescription = null,
        modifier = Modifier
            .size(100F.dp, 100F.dp)
            .offset(y = yOffsetAnimatable.value.dp)
    )

    LaunchedEffect(true) {
        yOffsetAnimatable.animateTo(
            targetValue = endValue,
            animationSpec = tween(1500)
        )
    }
}