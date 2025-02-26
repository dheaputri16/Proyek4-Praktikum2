package com.example.hanyarunrun.ui.screen

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hanyarunrun.R
import kotlinx.coroutines.delay
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Text
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hanyarunrun.ui.theme.HanyarunrunTheme

@SuppressLint("CustomSplashScreen")
@Composable
fun SplashScreen(navigateToHome: () -> Unit) {
    var startAnimation by remember { mutableStateOf(false) }

    val scale = remember { Animatable(0f) }
    val rotation = remember { Animatable(0f) }
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        // Fade in effect
        alpha.animateTo(1f, animationSpec = tween(1000))

        // Scale up effect
        scale.animateTo(1.2f, animationSpec = tween(1200, easing = EaseOutBack))

        // Rotation effect
        rotation.animateTo(360f, animationSpec = tween(1500, easing = LinearEasing))

        // Tunggu sebentar lalu navigasi ke Home
        delay(1000)
        navigateToHome()
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val logo: Painter = painterResource(id = com.example.hanyarunrun.R.drawable.logoapp) // Sesuaikan dengan gambar kamu
            Image(
                painter = logo,
                contentDescription = "Logo Splash Screen",
                modifier = Modifier
                    .size(150.dp)
                    //.alpha(alpha) // Animasi fade-in
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.background), // Gambar latar belakang
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Content di atas background
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val logo = painterResource(id = R.drawable.logoapp)

            Image(
                painter = logo,
                contentDescription = "Logo Splash Screen",
                modifier = Modifier
                    .size(150.dp)
                    .scale(scale.value) // Efek scale
                    .alpha(alpha.value) // Efek fade-in
                    .rotate(rotation.value) // Efek rotasi
            )

            Spacer(modifier = Modifier.height(16.dp)) // Jarak antara logo dan teks


            Text(
                text = "DHEA PUTRI ANANDA",
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.shadow(4.dp, shape = RoundedCornerShape(8.dp))
            )
        }
    }
}

@Preview
@Composable
fun PreviewSplashScreen() {
    HanyarunrunTheme {
        SplashScreen(navigateToHome = {})
    }
}
