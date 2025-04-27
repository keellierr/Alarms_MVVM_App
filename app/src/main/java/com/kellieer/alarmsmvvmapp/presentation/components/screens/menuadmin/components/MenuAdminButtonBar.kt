package com.kellieer.alarmsmvvmapp.presentation.components.screens.menuadmin.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kellieer.alarmsmvvmapp.R
import com.kellieer.alarmsmvvmapp.presentation.navegation.AppScreens


@Composable
fun MenuAdminButtonBar(
    navController: NavHostController
) {
    var showSearchBar by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }



    val isPressed = remember { mutableStateOf(false) }
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val isOnMenUserScreen = currentRoute == AppScreens.MenuAdminScreen.route

    if (isOnMenUserScreen) {
        isPressed.value = true
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {




        // Barra de navegación inferior
        BottomAppBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth().height(52.dp)
        ) {
            IconButton(onClick = {  },
                modifier = Modifier
                    .background(
                        if (isPressed.value) Color(0xFF6B469D) else Color.Transparent,
                        shape = CircleShape
                    )
                    .padding(8.dp).size(28.dp)
                ) {
                Icon(
                    painter = painterResource(id = R.drawable.folder),
                    contentDescription = "Buscar",
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f, true))
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.favorite),
                    contentDescription = "Buscar",
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f, true))
            IconButton(onClick = { navController.navigate(AppScreens.EditRegisterScreen.route) }) {
                Icon(
                    painter = painterResource(id = R.drawable.account),
                    contentDescription = "Perfil",
                    modifier = Modifier.size(18.dp)
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
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp), // Espacio entre botones
                horizontalAlignment = Alignment.End
            ) {
                FloatingActionButton(
                    onClick = { /* Acción para cupón */ },
                    modifier = Modifier.size(56.dp),
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.add),
                        contentDescription = "Cupón",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

    }
}

