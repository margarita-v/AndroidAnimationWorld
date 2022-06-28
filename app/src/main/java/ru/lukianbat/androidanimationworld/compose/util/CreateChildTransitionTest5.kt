package ru.lukianbat.androidanimationworld.compose.util

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ru.lukianbat.androidanimationworld.R

@ExperimentalTransitionApi
@Composable
fun CreateChildTransitionTest5() {
    // TODO 5 Декомпозиция составных анимаций с помощью createChildTransition

    var parentState by remember { mutableStateOf(ParentState.InitialState) }
    val parentTransition = updateTransition(parentState, label = "")

    val firstAnimationTransition = parentTransition.createChildTransition {
        it != ParentState.InitialState
    }
    val secondAnimationTransition = parentTransition.createChildTransition {
        it == ParentState.SecondParentState
    }

    val alpha by firstAnimationTransition.animateFloat(
        transitionSpec = { tween(durationMillis = 3000) },
        label = ""
    ) { if (it) 1F else 0F }

    val yOffset by secondAnimationTransition.animateFloat(
        transitionSpec = { tween(durationMillis = 3000) },
        label = ""
    ) { if (it) 400F else 100F }

    Image(
        painter = painterResource(R.drawable.ic_podlodka),
        contentDescription = "firstIcon",
        modifier = Modifier
            .size(100F.dp, 100F.dp)
            .offset(y = yOffset.dp)
            .alpha(alpha)
    )

    LaunchedEffect(true) {
        parentState = ParentState.FirstParentState
        delay(3000)
        parentState = ParentState.SecondParentState
    }
}

enum class ParentState {
    InitialState,
    FirstParentState,
    SecondParentState
}