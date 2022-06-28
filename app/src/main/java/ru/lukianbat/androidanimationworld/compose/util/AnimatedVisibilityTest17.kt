package ru.lukianbat.androidanimationworld.compose.util

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.lukianbat.androidanimationworld.R

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityTest17() {
    // TODO 17 Анимация появления контента с дополнительными анимациями Transition

    var visible by remember { mutableStateOf(true) }
    AnimatedVisibility(
        visible = visible,
        enter = slideIn(
            animationSpec = tween(2000),
            initialOffset = { IntOffset(0, -it.height) }
        ),
        exit = slideOut(
            animationSpec = tween(2000),
            targetOffset = { IntOffset(0, -it.width) }
        ),
    ) {
        val background by transition.animateColor(
            label = "",
            transitionSpec = { tween(2000) }
        ) { state ->
            if (state == EnterExitState.Visible) Color.Green else Color.Red
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(background)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_podlodka),
                contentDescription = null,
                modifier = Modifier
                    .size(200F.dp, 200F.dp)
            )
            Text(
                "Hello Podlodka!!!",
                fontSize = 48.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .wrapContentWidth()
            )
        }
    }

    Button(
        onClick = {
            visible = visible.not()
        },
        modifier = Modifier.offset(y = 28.dp),
    ) {
        Text(
            "Запустить анимацию",
            fontSize = 21.sp,
            modifier = Modifier
                .wrapContentWidth()
        )
    }
}