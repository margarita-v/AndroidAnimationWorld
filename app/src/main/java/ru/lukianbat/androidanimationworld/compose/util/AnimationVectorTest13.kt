package ru.lukianbat.androidanimationworld.compose.util

import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.lukianbat.androidanimationworld.R

@Composable
fun AnimationVectorTest13() {
    // TODO 13 Анимация изменения кастомного типа с двумя параметрами MySize с помощью AnimationVector2D

    var startAnimation by remember { mutableStateOf(false) }

    val animSize: MySize by animateValueAsState(
        targetValue = if (startAnimation) MySize(300.dp, 600.dp) else MySize(100.dp, 50.dp),
        typeConverter = TwoWayConverter(
            convertToVector = { size: MySize ->
                AnimationVector2D(size.width.value, size.height.value)
            },
            convertFromVector = { vector: AnimationVector2D ->
                MySize(vector.v1.dp, vector.v2.dp)
            }
        ),
        animationSpec = tween(durationMillis = 3000, delayMillis = 1000)
    )

    Image(
        painter = painterResource(R.drawable.ic_podlodka),
        contentDescription = null,
        modifier = Modifier
            .size(animSize.width, animSize.height)
    )

    Button(onClick = {
        startAnimation = startAnimation.not()
    }, modifier = Modifier.offset(y = 600.dp)) {
        Text("Change startAnimation state")
    }
}

data class MySize(
    val width: Dp,
    val height: Dp
)