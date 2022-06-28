package ru.lukianbat.androidanimationworld.compose.util

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import ru.lukianbat.androidanimationworld.R

@ExperimentalMaterialApi
@ExperimentalMotionApi
@Composable
fun MotionLayoutComposeTest23() {
    // TODO 23 MotionLayout

    val screenHeight = LocalConfiguration.current.screenHeightDp.toFloat()

    val swipingState = rememberSwipeableState(initialValue = SwipingStates.EXPANDED)

    val animateMotionLayoutProgress by animateFloatAsState(
        targetValue = if (swipingState.progress.to == SwipingStates.COLLAPSED) {
            swipingState.progress.fraction
        } else {
            1f - swipingState.progress.fraction
        },
       // animationSpec = spring(Spring.DampingRatioHighBouncy)
    )

    MotionLayout(
        start = startConstraintSet(),
        end = endConstraintSet(),
        progress = animateMotionLayoutProgress,
        modifier = Modifier
            .fillMaxWidth()
            .height(screenHeight.dp)
            .swipeable(
                state = swipingState,
                thresholds = { _, _ -> FractionalThreshold(0.5f) },
                orientation = Orientation.Vertical,
                anchors = mapOf(
                    0f to SwipingStates.COLLAPSED,
                    screenHeight to SwipingStates.EXPANDED,
                )
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_podlodka),
            contentDescription = "",
            modifier = Modifier
                .layoutId("podlodkaImage")
                .background(MaterialTheme.colors.primary)
                .alpha(alpha = 1f - animateMotionLayoutProgress),
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = "Hi, podlodka!",
            modifier = Modifier
                .layoutId("title")
                .wrapContentHeight(),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
    }
}

enum class SwipingStates {
    EXPANDED,
    COLLAPSED
}

private fun startConstraintSet() = ConstraintSet {
    val podlodkaImage = createRefFor("podlodkaImage")
    val title = createRefFor("title")

    constrain(podlodkaImage) {
        width = Dimension.fillToConstraints
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        top.linkTo(parent.top)
    }

    constrain(title) {
        start.linkTo(parent.start, 16.dp)
        top.linkTo(podlodkaImage.bottom, 16.dp)
    }
}

private fun endConstraintSet() = ConstraintSet {
    val podlodkaImage = createRefFor("podlodkaImage")
    val title = createRefFor("title")

    constrain(podlodkaImage) {
        width = Dimension.fillToConstraints
        height = Dimension.value(56.dp)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        top.linkTo(parent.top)
    }

    constrain(title) {
        start.linkTo(parent.start)
        top.linkTo(parent.top, 8.dp)
        end.linkTo(parent.end)
        bottom.linkTo(podlodkaImage.bottom)
    }
}