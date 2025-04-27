package com.kellieer.alarmsmvvmapp.presentation.components.screens.menuadmin.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kellieer.alarmsmvvmapp.R
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.kellieer.alarmsmvvmapp.presentation.navegation.AppScreens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuTopBarMenu(navController: NavHostController) {
    Surface(
        modifier = Modifier
            .fillMaxWidth().offset(y = 45.dp)
            .height(80.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(50),
        color = Color(0xFFF2ECF7)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menú"
            )

            Text(
                text = "Reportes pendientes",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.width(160.dp))

            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "Salir",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        navController.navigate(AppScreens.LoginScreen.route) {
                            popUpTo(0) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
            )
        }
    }
}