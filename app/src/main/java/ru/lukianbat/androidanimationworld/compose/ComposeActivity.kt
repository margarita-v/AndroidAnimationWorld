package ru.lukianbat.androidanimationworld.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import ru.lukianbat.androidanimationworld.R
import ru.lukianbat.androidanimationworld.compose.util.MotionLayoutComposeTest23

@OptIn(ExperimentalTransitionApi::class)
class ComposeActivity : ComponentActivity() {

    @ExperimentalMaterialApi
    @ExperimentalMotionApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //TargetBasedAnimationForPerfomanceTest()
                MotionLayoutComposeTest23()
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalMotionApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MotionLayoutComposeTest23()
    }
}

@Composable
fun TargetBasedAnimationForPerfomanceTest() {
    var offset by remember { mutableStateOf(0F) }
    var startAnimation by remember { mutableStateOf(false) }

    val anim = remember {
        TargetBasedAnimation(
            animationSpec = tween(
                durationMillis = 3000,
                easing = LinearEasing
            ),
            typeConverter = Float.VectorConverter,
            initialValue = 0f,
            targetValue = 300f
        )
    }

    LaunchedEffect(startAnimation) {
        var playTime: Long
        val startTime = withFrameNanos { it }
        do {
            playTime = withFrameNanos { it } - startTime
            offset = anim.getValueFromNanos(playTime)
        } while (!anim.isFinishedFromNanos(playTime))
    }

    Image(
        painter = painterResource(R.drawable.ic_podlodka),
        contentDescription = "",
        modifier = Modifier
            .size(100F.dp, 100F.dp)
            .offset(y = offset.dp)
    )
}
