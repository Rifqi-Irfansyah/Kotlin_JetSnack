@file:OptIn(
    ExperimentalSharedTransitionApi::class
)

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.runtime.Composable
import android.os.Bundle
import android.text.style.BackgroundColorSpan
import android.widget.HorizontalScrollView
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.airbnb.lottie.model.content.GradientFill
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
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {
        item { HeaderSection() }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item { LayananSection() }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item { ArtikelSection() }
    }
}
@Composable
fun HeaderSection() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.hirobot))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
    )
    val gradientColors = listOf(
        Primary,
        Color(0xFFFF0000)
    )
    Box(
        modifier = Modifier
            .height(220.dp)
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = Brush.radialGradient(
                    colors = gradientColors,
                    center = androidx.compose.ui.geometry.Offset(0.5f, 0.5f),
                    radius = 2000f,
                    tileMode = TileMode.Clamp
                )
            )
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
            .background(Color.White)
            .border(
                border = BorderStroke(2.dp, Primary),
                shape = RoundedCornerShape(16.dp)
            )
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
fun ArtikelSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Artikel",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column {
            artikel.forEach { item ->
                ArticleCard(item)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun ArticleCard(artikel: Artikel) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Gray)
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(artikel.websiteUrl))
                context.startActivity(intent)
            }
    ) {
        Image(
            painter = painterResource(artikel.imageRes),
            contentDescription = null,
            modifier = Modifier.height(180.dp),
            contentScale = ContentScale.Crop,
        )
    }
}

data class Layanan(val imageRes: Int)
data class Artikel(val imageRes: Int, val websiteUrl: String)

val layanan = listOf(
    Layanan(R.drawable.kaiservices),
    Layanan(R.drawable.kaibandara),
    Layanan(R.drawable.kaicommuter),
    Layanan(R.drawable.kaiwisata),
    Layanan(R.drawable.kailogistik),
    Layanan(R.drawable.kaiproperti)
)

val artikel = listOf(
    Artikel(R.drawable.content1, "https://penumpang.kai.id/promo?id=501"),
    Artikel(R.drawable.content2, "https://penumpang.kai.id/promo?id=403"),
    Artikel(R.drawable.content3, "https://penumpang.kai.id/promo?id=632"),
    Artikel(R.drawable.content4, "https://penumpang.kai.id/promo?id=608"),
)

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MaterialTheme {
        Beranda()
    }
}
