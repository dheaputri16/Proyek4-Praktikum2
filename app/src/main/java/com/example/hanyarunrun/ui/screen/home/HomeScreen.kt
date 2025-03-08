package com.example.hanyarunrun.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hanyarunrun.R
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Pantau Harga Barang", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.White)
            ) }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "246 Results", fontSize = 16.sp)
                Row {
                    Button(onClick = { /* Sort action */ }) { Text("Sort") }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = { /* Filter action */ }) { Text("Filter") }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            ProductGrid()
        }
    }
}

@Composable
fun ProductGrid() {
    val products = listOf(
        Product("Small Plant Sill for Desk", "Beautiful made from wood and fits up to 3 plants!", 89.99, R.drawable.logoapp),
        Product("Agent Mug", "Handcrafted porcelain mug, Handcrafted porcelain mug", 19.00, R.drawable.logoapp),
        Product("Hardwood Stool", "Classic design, timeless material", 99.00, R.drawable.logoapp),
        Product("Ceramic Plate Set", "Hand made with a textured glaze", 59.99, R.drawable.logoapp)
    )
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
        items(products) { product ->
            ProductCard(product)
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(product.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(product.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(product.description, fontSize = 12.sp, color = MaterialTheme.colorScheme.secondary)
            Spacer(modifier = Modifier.height(4.dp))
            Text("$${product.price}", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
    }
}

data class Product(val name: String, val description: String, val price: Double, val image: Int)