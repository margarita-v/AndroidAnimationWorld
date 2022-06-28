package ru.lukianbat.androidanimationworld.compose.util

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.lukianbat.androidanimationworld.R

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityTest16() {
    // TODO 16 Анимация появления контента с помощью AnimatedVisibility и animateEnterExit

    var visible by remember { mutableStateOf(true) }
    AnimatedVisibility(visible = visible) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(R.drawable.ic_podlodka),
                contentDescription = null,
                modifier = Modifier
                    .size(200F.dp, 200F.dp)
                    .animateEnterExit(
                        enter = slideInVertically(),
                        exit = slideOutVertically()
                    )
            )
            Text(
                "Hello Podlodka!!!",
                fontSize = 48.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .wrapContentWidth()
                    .animateEnterExit(
                        enter = fadeIn(),
                        exit = fadeOut()
                    )
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