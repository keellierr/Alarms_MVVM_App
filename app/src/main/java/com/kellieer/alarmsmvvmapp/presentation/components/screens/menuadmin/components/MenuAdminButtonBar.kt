package com.kellieer.alarmsmvvmapp.presentation.components.screens.menuadmin.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.kellieer.alarmsmvvmapp.R
import com.kellieer.alarmsmvvmapp.presentation.navegation.AppScreens


@Composable
fun MenuAdminButtonBar(
    navController: NavHostController
) {
    var showSearchBar by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {




        // Barra de navegación inferior
        BottomAppBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth().height(52.dp)
        ) {
            IconButton(onClick = {  }) {
                Icon(
                    painter = painterResource(id = R.drawable.folder),
                    contentDescription = "Buscar",
                    modifier = Modifier.size(32.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f, true))
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.saved),
                    contentDescription = "Buscar",
                    modifier = Modifier.size(32.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f, true))
            IconButton(onClick = { navController.navigate(AppScreens.EditRegisterScreen.route) }) {
                Icon(
                    painter = painterResource(id = R.drawable.account),
                    contentDescription = "Perfil",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
        // Botones flotantes para añadir cupones y eventos
        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 16.dp, bottom = 72.dp)
        ) {
            FloatingActionButton(
                onClick = {  },
                modifier = Modifier.size(56.dp),
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.error),
                    contentDescription = "Añadir Cupón",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}




@Composable
fun EventCard(
    title: String,
    description: String,
    date: String,
    address: String,
    city: String,
    isHighlighted: Boolean
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(500.dp)
            .shadow(if (isHighlighted) 10.dp else 2.dp, RoundedCornerShape(10.dp)),
        elevation = if (isHighlighted) CardDefaults.cardElevation(8.dp) else CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isHighlighted) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Text(text = description, style = MaterialTheme.typography.bodyMedium)
            Text(text = "Fecha: $date", style = MaterialTheme.typography.bodySmall)
            Text(text = "Dirección: $address", style = MaterialTheme.typography.bodySmall)
            Text(text = "Ciudad: $city", style = MaterialTheme.typography.bodySmall)
        }
    }
}


@Composable
fun CouponCard(
    name: String,
    stock: Int,
    startDate: String,
    endDate: String,
    salePrice: Double,
    isHighlighted: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .width(500.dp)
            .shadow(if (isHighlighted) 10.dp else 2.dp, RoundedCornerShape(10.dp)),
        elevation = if (isHighlighted) CardDefaults.cardElevation(8.dp) else CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isHighlighted) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = name, style = MaterialTheme.typography.titleMedium)
            Text(text = "Stock: $stock", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Fecha Inicio: $startDate", style = MaterialTheme.typography.bodySmall)
            Text(text = "Fecha Fin: $endDate", style = MaterialTheme.typography.bodySmall)
            Text(text = "Precio: $salePrice", style = MaterialTheme.typography.bodySmall)
        }
    }
}
