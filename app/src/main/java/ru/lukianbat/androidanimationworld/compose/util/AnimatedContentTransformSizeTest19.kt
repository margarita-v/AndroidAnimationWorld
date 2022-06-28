package ru.lukianbat.androidanimationworld.compose.util

import androidx.compose.animation.*
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.lukianbat.androidanimationworld.R

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
@Composable
fun AnimatedContentTransformSizeTest19() {
    // TODO 19 Анимация изменения размера контента (раскрытия) с помощью AnimatedContent и SizeTransform
    var expanded by remember { mutableStateOf(false) }
    Surface(
        color = MaterialTheme.colors.primary,
        onClick = { expanded = !expanded },
        modifier = Modifier.offset(16.dp, 16.dp)
    ) {
        AnimatedContent(
            targetState = expanded,
            transitionSpec = {
                fadeIn(animationSpec = tween(150, 150)) with
                        fadeOut(animationSpec = tween(150)) using
                        SizeTransform { initialSize, targetSize ->
                            if (targetState) {
                                keyframes {
                                    // Expand horizontally first.
                                    IntSize(targetSize.width, initialSize.height) at 150
                                    durationMillis = 300
                                }
                            } else {
                                keyframes {
                                    // Shrink vertically first.
                                    IntSize(initialSize.width, targetSize.height) at 150
                                    durationMillis = 300
                                }
                            }
                        }
            }
        ) { targetExpanded ->
            if (targetExpanded) {
                Text(
                    text = "Привет, подлодка! Привет, подлодка! Привет, подлодка! \n" +
                            " Привет, подлодка! Привет, подлодка! Привет, подлодка! \n" +
                            "Привет, подлодка! Привет, подлодка! Привет, подлодка! \n",
                    fontSize = 16.sp
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.ic_podlodka),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100F.dp, 100F.dp)
                )
            }
        }
    }
}