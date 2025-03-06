package com.example.jetsnack.ui.home.penumpang

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetsnack.model.PenumpangKereta

@Composable
fun Penumpang(
    viewModel: MainViewModel = viewModel(),
) {
    val users by viewModel.users.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Daftar Penumpang", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        if (users.isEmpty()) {
            Text(text = "Tidak ada data", fontSize = 16.sp, fontWeight = FontWeight.Light)
        } else {
            UserList(users, modifier = Modifier.padding(top = 8.dp))
        }
    }
}

@Composable
fun UserList(users: List<PenumpangKereta>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.padding(16.dp)) {
        items(users) { user ->
            UserItem(user)
        }
    }
}

@Composable
fun UserItem(user: PenumpangKereta) {
    Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Name: ${user.nama_stasiun}",
                style = MaterialTheme.typography.titleLarge)

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MaterialTheme {
        Penumpang()
    }
}
