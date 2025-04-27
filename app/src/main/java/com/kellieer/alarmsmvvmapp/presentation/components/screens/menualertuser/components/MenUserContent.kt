package com.kellieer.alarmsmvvmapp.presentation.components.screens.menualertuser.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.kellieer.alarmsmvvmapp.R
import com.kellieer.alarmsmvvmapp.presentation.components.DefaultButton
import com.kellieer.alarmsmvvmapp.presentation.components.DefaultDatePickerDocked
import com.mapbox.maps.extension.style.expressions.dsl.generated.mod

@Composable
fun MenUserContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth().offset(y= -10.dp)
                .padding()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ReportFiltersRow()
            Spacer(modifier = Modifier.height(90.dp))
            val totalItems = 2
            val items = List(totalItems) { it }
            items.chunked(2).forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    rowItems.forEach {
                        CardItem()
                    }

                    // Si solo hay 1 ítem en esta fila, agregar espacio para centrar
                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.width(180.dp)) // Ajusta según el tamaño de tus Cards
                    }
                }
            }
        }
    }
}

@Composable
fun CardItem() {
    var showDialog by remember { mutableStateOf(false) }
    var birthDate by remember { mutableStateOf("") }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(160.dp) // Ancho fijo para permitir dos por fila
            .height(250.dp)
            .then(
                if (showDialog) Modifier.border(2.dp, Color(0xFFB388EB), RoundedCornerShape(16.dp))
                else Modifier
            )
            .clip(RoundedCornerShape(16.dp))
            .clickable { showDialog = true },
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.cuatro),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Reporte",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Lorem Ipsum is simply dummy text.",
                style = MaterialTheme.typography.bodySmall,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(510.dp)
                    .offset(y = 30.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.cuatro),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Detalles de Drake",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    DefaultDatePickerDocked(onDateSelected = { birthDate = it })
                    Spacer(modifier = Modifier.height(16.dp))
                    DefaultButton(text = "Reservar", onClick = {})
                }
            }
        }
    }
}


@Composable
fun ReportFiltersRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth().offset(y=110.dp, x = -48.dp)
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { /* Acción pendientes */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5E4B8B)),
            shape = RoundedCornerShape(50),
            modifier = Modifier.padding(end = 8.dp)
        ) {
            Text("Pendientes", color = Color.White)
        }

        Button(
            onClick = { /* Acción verificados */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5E4B8B)),
            shape = RoundedCornerShape(50)
        ) {
            Text("Verificados", color = Color.White)
        }
    }
}

