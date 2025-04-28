package com.kellieer.alarmsmvvmapp.presentation.components.screens.showpendingresolvedalert.components

import android.R.attr.iconTint
import android.util.Log
import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kellieer.alarmsmvvmapp.presentation.navegation.AppScreens

@Composable
fun ShowPendingResolvedContent() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            val isDarkTheme = isSystemInDarkTheme()

            Text(
                text = "Marque (✓) SI se resolvió el reporte sino (X)",
                modifier = Modifier
                    .offset(y = 140.dp, x = 28.dp),
                color = if (isDarkTheme) Color.White else Color.Black
            )
            repeat(6) {
                CardItem()
            }
        }
    }
}

@Composable
fun CardItem() {
    val isDarkTheme = isSystemInDarkTheme() // Detectar modo oscuro

    // Definir colores dinámicamente
    val backgroundColor = if (isDarkTheme) Color(0xFF2C2C2E) else Color(0xFFF5EFFF) // Fondo de la tarjeta
    val iconBackground = if (isDarkTheme) Color(0xFF3C3C3E) else Color(0xFFEADDF6) // Fondo del círculo
    val iconColor = if (isDarkTheme) Color(0xFF7E57C2) else Color(0xFF6B469D) // Ícono adaptado
    val iconTint = if (isDarkTheme) Color(0xFFD1B3FF) else Color(0xFF6B469D)

    Card(
        modifier = Modifier
            .fillMaxWidth().offset(y = 154.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            // Ícono circular con fondo
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(iconBackground),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Alerta",
                    tint = iconColor
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Header",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Subhead",
                    style = MaterialTheme.typography.bodySmall,
                    color = if (isDarkTheme) Color.LightGray else Color.Gray
                )
            }

            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Ir a detalle",
                tint = iconTint,
                modifier = Modifier
                    .size(30.dp).offset(x=70.dp)
            )

            Spacer(modifier = Modifier.width(80.dp))

            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Ir a detalle",
                tint = iconTint,
                modifier = Modifier
                    .size(30.dp).offset()
            )
        }
    }
}