package ru.lukianbat.androidanimationworld.compose.util

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContentTest18() {
    // TODO 18 Анимация изменения контента (увеличение числа) с помощью AnimatedContent
    var count by remember { mutableStateOf(0) }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AnimatedContent(
            targetState = count,
            transitionSpec = {
                if (targetState > initialState) {
                    slideInVertically { height -> height } with
                            slideOutVertically { height -> -height }
                } else {
                    slideInVertically { height -> -height } with
                            slideOutVertically { height -> height }
                }
            }
        ) { targetCount ->
            val background by transition.animateColor(
                label = "",
                transitionSpec = { tween(2000) }
            ) { state ->
                if (state == EnterExitState.Visible) Color.Green else Color.Red
            }
            // Важно использовать targetCount
            Text(
                fontSize = 48.sp,
                color = background,
                text = "$targetCount"
            )
        }

        Button(
            onClick = { count++ },
            Modifier.offset(y = 28.dp)
        ) {
            Text(
                text = "Plus",
                fontSize = 21.sp,
            )
        }
        Button(
            onClick = { count-- },
            Modifier.offset(y = 28.dp)
        ) {
            Text(
                text = "Minus",
                fontSize = 21.sp,
            )
        }
    }
}