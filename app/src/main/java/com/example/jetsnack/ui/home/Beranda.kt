@file:OptIn(
    ExperimentalSharedTransitionApi::class
)

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.runtime.Composable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.jetsnack.R
import com.example.jetsnack.ui.theme.Primary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Beranda()
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Beranda() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 56.dp)
        ) {
            HeaderSection()
            Spacer(modifier = Modifier.height(16.dp))
            LayananSection()
            Spacer(modifier = Modifier.height(16.dp))
            RecommendationsSection()
        }
}

@Composable
fun HeaderSection() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.hirobot))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
    )

    Box(
        modifier = Modifier
            .height(220.dp)
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Primary)
    ) {
        Row{
            LottieAnimation(
                composition = composition,
                progress = progress,
                alignment = Alignment.BottomStart,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(1f/2f)
                    .align(Alignment.Bottom)
            )
            Text(
                text = "Welcome\nTo Our Apps",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun LayananSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Layanan",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow {
            items(layanan.size) { index ->
                LayananCard(layanan[index])
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Composable
fun LayananCard(tip: Layanan) {
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(120.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(
                border =
                BorderStroke(2.dp, Primary)
            )
            .background(Color.White)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(tip.imageRes),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
        }
    }
}

@Composable
fun RecommendationsSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Rekomendasi Untukmu",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(recommendations.size) { index ->
                RecommendationCard(recommendations[index])
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun RecommendationCard(recommendation: Recommendation) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Gray)
            .clickable { }
    ) {
        Image(
            painter = painterResource(recommendation.imageRes),
            contentDescription = null,
            modifier = Modifier.height(180.dp),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f))
                .padding(16.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Column {
                Text(
                    text = recommendation.title,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = recommendation.author,
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }
    }
}

data class Layanan(val imageRes: Int)
data class Recommendation(val title: String, val author: String, val imageRes: Int)

val layanan = listOf(
    Layanan(R.drawable.kaiservices),
    Layanan(R.drawable.kaibandara),
    Layanan(R.drawable.kaicommuter),
    Layanan(R.drawable.kaiwisata),
    Layanan(R.drawable.kailogistik),
    Layanan(R.drawable.kaiproperti)
)

val recommendations = listOf(
    Recommendation("Cara Mudah Belajar Berenang", "Diah Ayu", R.drawable.chips),
    Recommendation("Tips Latihan di Gym", "Andi", R.drawable.eclair)
)

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MaterialTheme {
        Beranda()
    }
}
