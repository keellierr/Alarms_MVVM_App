package com.kellieer.alarmsmvvmapp.presentation.components.screens.menualertuser.components

import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kellieer.alarmsmvvmapp.R
import com.kellieer.alarmsmvvmapp.presentation.navegation.AppScreens

@Composable
fun MenUserButtonBar(navController: NavHostController) {
    var showSearchBar by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    val context = LocalContext.current

    val isPressed = remember { mutableStateOf(false) }

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val isOnMenUserScreen = currentRoute == AppScreens.MenUserScreen.route

    if (isOnMenUserScreen) {
        isPressed.value = true
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (showSearchBar) {
            SearchBar(
                searchText = searchText,
                onTextChange = { searchText = it },
                onClose = {
                    showSearchBar = false
                    searchText = ""
                },
                modifier = Modifier
                    .align(Alignment.TopCenter)
            )
        }


        BottomAppBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth().height(52.dp)
        ) {
            IconButton(onClick = {

            },
                modifier = Modifier
                .background(
                    if (isPressed.value) Color(0xFF6B469D) else Color.Transparent,
                    shape = CircleShape
                )
                .padding(8.dp).size(28.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.location),
                    contentDescription = "Buscar",
                    modifier = Modifier.size(16.5.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f, true))
            IconButton(onClick = {

                navController.navigate(AppScreens.ShowSavedAlertScreen.route)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.saved),
                    contentDescription = "Buscar",
                    modifier = Modifier.size(16.5.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f, true))
            IconButton(onClick = { navController.navigate(AppScreens.EditRegisterScreen.route) }) {
                Icon(
                    painter = painterResource(id = R.drawable.account),
                    contentDescription = "Perfil",
                    modifier = Modifier.size(16.5.dp)
                )
            }
        }
        IconButton(

            onClick = {
                navController.navigate("QuicklyWorldAlertScreen")  },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-22).dp, y = (-140).dp)
                .size(40.dp)
                .background(Color(0xFF7E57C2), shape = CircleShape) // fondo morado
        ) {
            Icon(
                painter = painterResource(id = R.drawable.globe), // asegúrate de tener este ícono
                contentDescription = "Mapa del mundo",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }


        FloatingActionButton(
            onClick = { navController.navigate("RegisterAlertScreen") },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 72.dp),
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(
                painter = painterResource(id = R.drawable.add),
                contentDescription = "Añadir",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(26.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    searchText: String,
    onTextChange: (String) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize().offset(y=-259.dp).offset(x = 12.dp)// Hace que el Column ocupe todo el espacio disponible
            .padding(),
        verticalArrangement = Arrangement.Center, // Centra el contenido verticalmente
        horizontalAlignment = Alignment.CenterHorizontally // Centra el contenido horizontalmente
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(142.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                TextField(
                    value = searchText,
                    onValueChange = onTextChange,
                    placeholder = { Text("Buscar...") },
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 36.dp),
                    singleLine = true
                )
                IconButton(onClick = onClose) {
                    Icon(
                        painter = painterResource(id = R.drawable.cancel),
                        contentDescription = "Cerrar"
                    )
                }
            }
        }
    }
}



