package com.kellieer.alarmsmvvmapp.presentation.components.screens.showrefusealert.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kellieer.alarmsmvvmapp.presentation.navegation.AppScreens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.kellieer.alarmsmvvmapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowRefuseTopBar(navController: NavHostController) {
    val isDarkTheme = isSystemInDarkTheme()
    val backgroundColor = if (isDarkTheme) Color(0xFF1C1C1E) else Color(0xFFF2ECF7)
    val contentColor = if (isDarkTheme) Color.White else Color.Black

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(scope, drawerState, isDarkTheme, navController)
        }
    ) {
        TopBarContent(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            onMenuClick = { scope.launch { drawerState.open() } }
        )
    }
}

@Composable
fun DrawerContent(
    scope: CoroutineScope,
    drawerState: DrawerState,
    isDarkTheme: Boolean,
    navController: NavHostController,
    selectedItem: MutableState<String> = remember { mutableStateOf("Rechazados") }
) {
    val backgroundColor = if (isDarkTheme) Color(0xFF1C1C1E) else Color(0xFFF9F5FC)
    val selectedColor = if (isDarkTheme) Color(0xFF7E57C2) else Color(0xFFD1C4E9)
    val textColor = if (isDarkTheme) Color.White else Color.Black

    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .width(280.dp)
            .padding(top = 40.dp, bottom = 20.dp),
        shape = RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp),
        color = backgroundColor
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            val items = listOf(
                "Explorar reportes nuevos" to Icons.Default.Notifications,
                "Mis reportes" to Icons.Default.List,
                "Guardados" to Icons.Default.Favorite,
                "Rechazados" to Icons.Default.Info,
                "Eliminados" to Icons.Default.Delete
            )

            items.forEach { (label, icon) ->
                val isSelected = selectedItem.value == label
                val rowColor = if (isSelected) selectedColor else Color.Transparent
                val iconColor = if (isSelected) Color.White else if (isDarkTheme) Color.LightGray else Color.DarkGray

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(rowColor, RoundedCornerShape(12.dp))
                        .clickable {
                            selectedItem.value = label
                            scope.launch { drawerState.close() }
                            when (label) {
                                "Explorar reportes nuevos" -> {
                                    navController.navigate(AppScreens.MenUserScreen.route)
                                }
                                "Mis reportes" -> {
                                    navController.navigate(AppScreens.MenuAlertUserScreen.route)
                                }
                                "Guardados" -> {
                                    navController.navigate(AppScreens.ShowSavedAlertScreen.route)
                                }
                                "Eliminados" -> {
                                    navController.navigate(AppScreens.ShowRemoveAlertScreen.route)
                                }
                            }
                        }
                        .padding(vertical = 12.dp, horizontal = 8.dp)
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        tint = iconColor
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = label,
                        color = if (isSelected) Color.White else textColor,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

@Composable
fun TopBarContent(
    backgroundColor: Color,
    contentColor: Color,
    onMenuClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = 45.dp)
            .height(80.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(50),
        color = backgroundColor
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menú",
                tint = contentColor,
                modifier = Modifier.clickable { onMenuClick() }
            )

            Text(
                text = "Buscar rechazados",
                style = MaterialTheme.typography.bodyMedium,
                color = contentColor
            )

            Spacer(modifier = Modifier.width(125.dp))

            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "Buscar",
                tint = contentColor,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
