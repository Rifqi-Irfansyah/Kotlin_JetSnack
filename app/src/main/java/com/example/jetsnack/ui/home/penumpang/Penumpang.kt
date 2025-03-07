package com.example.jetsnack.ui.home.penumpang

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetsnack.R
import com.example.jetsnack.model.PenumpangKereta
import com.example.jetsnack.ui.theme.Primary

@Composable
fun Penumpang(
    viewModel: MainViewModel = viewModel(),
) {
    val users by viewModel.users.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Jumlah Penumpang", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        if (users.isEmpty()) {
            Text(text = "Tidak ada data", fontSize = 16.sp, fontWeight = FontWeight.Light)
        } else {
            UserList(users, modifier = Modifier.padding(top = 8.dp))
        }
    }
}

@Composable
fun UserList(users: List<PenumpangKereta>, modifier: Modifier = Modifier) {
    LazyColumn (
        modifier = Modifier
            .padding(16.dp, 16.dp, 16.dp, 50.dp)
    ){
        items(users) { user ->
            UserItem(user)
        }
    }
}

@Composable
fun UserItem(user: PenumpangKereta) {
    var showDialog by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .height(150.dp)
            .clickable { showDialog = true },
    ) {
        val context = LocalContext.current
        val imageId = remember(user.nama_stasiun) {
            context.resources.getIdentifier(
                user.nama_stasiun.lowercase(),
                "drawable",
                context.packageName
            )
        }
        Image(
            painter = if (imageId != 0) painterResource(id = imageId) else painterResource(id = R.drawable.kereta),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .matchParentSize()
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Black.copy(alpha = 0.7f), Color.Transparent),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .matchParentSize()
        ) {
            Text(
                text = user.nama_stasiun,
                fontSize = 20.sp,
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "\nJumlah: ${user.penumpang_naik_kereta}" +
                        "\nTahun : ${user.tahun}",
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        if (showDialog) {
            Dialog(onDismissRequest = { showDialog = false }) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.4f))
                ) {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp)
                            .align(Alignment.Center),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Detail Penumpang",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text =
                                    "Tahun          : ${user.tahun}\n"+
                                    "Provinsi       : ${user.nama_provinsi}\n" +
                                    "Stasiun        : ${user.nama_stasiun}\n" +
                                    "Penumpang Naik : ${user.penumpang_naik_kereta}\n" +
                                    "Penumpang Turun: ${user.penumpang_turun_kereta}\n",
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(
                                onClick = { showDialog = false },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Primary,
                                    contentColor = Color.Black
                                )
                            ) {
                                Text("Tutup",
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MaterialTheme {
        val dummyUsers = listOf(
            PenumpangKereta(1, 32, "JAWA BARAT", "PURWAKARTA", 37205, 44125, "ORANG", 2022),
            PenumpangKereta(2, 32, "JAWA BARAT", "PLERED", 3211, 2876, "ORANG", 2022),
            PenumpangKereta(3, 32, "JAWA BARAT", "CIMAHI", 168393, 166528, "ORANG", 2022)
        )
        UserList(dummyUsers)
    }
}
