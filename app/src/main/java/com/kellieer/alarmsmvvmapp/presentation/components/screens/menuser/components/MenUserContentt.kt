package com.kellieer.alarmsmvvmapp.presentation.components.screens.menuser.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.kellieer.alarmsmvvmapp.R
import androidx.compose.material3.Icon
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.kellieer.alarmsmvvmapp.presentation.navegation.AppScreens

@Composable
fun MenUserContentt(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 136.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        repeat(2) {
            CardItem(navController)
        }
    }
}

@Composable
fun CardItem(navController: NavHostController) {
    val context = LocalContext.current
    val isDark = isSystemInDarkTheme()
    val interactionSource = remember { MutableInteractionSource() }

    val backgroundColor = if (isDark) Color(0xFF2A223A) else Color(0xFFF5EFFF)
    val iconBackgroundColor = if (isDark) Color(0xFF3E2E5C) else Color(0xFFEADDF6)
    val iconTint = if (isDark) Color(0xFFD1B3FF) else Color(0xFF6B469D)
    val titleColor = if (isDark) Color.White else Color.Black
    val subtitleColor = if (isDark) Color(0xFFCCCCCC) else Color.Gray

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable {
                Log.d("CARD_CLICK", "Clic detectado: navegando a ShowAlertScreen")
                Toast.makeText(context, "Navegando a ShowAlertScreen...", Toast.LENGTH_SHORT).show()
                navController.navigate(AppScreens.ShowAlertScreen.route)
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(iconBackgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Alerta",
                    tint = iconTint
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Alerta Accidente",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = titleColor
                )
                Text(
                    text = "Ubicación: Tebaida, Q",
                    style = MaterialTheme.typography.bodySmall,
                    color = subtitleColor
                )
            }

            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = R.drawable.cuatro),
                contentDescription = "Código 404",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Ir a detalle",
                tint = iconTint,
                modifier = Modifier
                    .size(30.dp)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null // puedes poner `rememberRipple()` si quieres feedback visual
                    ) {
                        Log.d("ICON_CLICK", "Clic en el ícono - Navegando")
                        Toast.makeText(context, "Flecha presionada", Toast.LENGTH_SHORT).show()
                        navController.navigate(AppScreens.ShowAlertScreen.route)
                    }
            )



        }
    }
}



