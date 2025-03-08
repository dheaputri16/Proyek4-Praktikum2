package com.example.hanyarunrun.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.example.hanyarunrun.viewmodel.DataViewModel
import com.example.hanyarunrun.R

@Composable
fun DataListScreen(navController: NavHostController, viewModel: DataViewModel) {
    val dataList by viewModel.dataList.observeAsState(emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("form") },
                containerColor = Color(0xFFFF69B4),
                shape = CircleShape
            ) {
                Icon(Icons.Default.Add, contentDescription = "Tambah Data", tint = Color.White)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFFFC0CB), Color(0xFFFF69B4))
                    )
                )
                .padding(innerPadding)
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logoapp),
                contentDescription = "Data Illustration",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(bottom = 12.dp)
            )

            if (dataList.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Data Tidak Tersedia",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(dataList) { item ->
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier
                                    .background(Color.White)
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = "Provinsi: ${item.namaProvinsi} (${item.kodeProvinsi})",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFFF69B4)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Kabupaten/Kota: ${item.namaKabupatenKota} (${item.kodeKabupatenKota})",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Total: ${item.total} ${item.satuan}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Tahun: ${item.tahun}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Button(
                                        onClick = { navController.navigate("edit/${item.id}") },
                                        shape = RoundedCornerShape(16.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF69B4)),
                                    ) {
                                        Text(text = "Edit", color = Color.White)
                                    }
                                    Button(
                                        onClick = { viewModel.deleteData(item) },
                                        shape = RoundedCornerShape(8.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF69B4))
                                    ) {
                                        Text(text = "Delete", color = Color.White)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}