package com.example.jetsnack.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import com.example.jetsnack.R
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.example.jetsnack.ui.theme.Primary

class SplashScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreenContent { navigateToMainScreen() }
        }
    }

    private fun navigateToMainScreen() {
        setContent {
            JetsnackApp()
        }
    }
}

@Composable
fun SplashScreenContent(onTimeout: () -> Unit = {}) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.cat))
    val progress by animateLottieCompositionAsState(composition)

    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 3000)
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(2800)
        onTimeout()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Primary
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(
                composition = composition,
                progress = progress,
                modifier = Modifier.fillMaxWidth(0.5f)
                    .height(300.dp)
                    .padding(0.dp, 50.dp, 0.dp, 0.dp)
            )
            Text(
                text = "  231511089\n" +
                        "Rifqi Irfansyah",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(0.dp, 250.dp, 0.dp, 0.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewSplashScreen() {
    SplashScreenContent()
}
