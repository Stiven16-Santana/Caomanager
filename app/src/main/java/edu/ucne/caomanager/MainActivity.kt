package edu.ucne.caomanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.caomanager.presentation.animal.AnimalRegistroScreen
import edu.ucne.caomanager.presentation.animal.AnimalScreen
import edu.ucne.caomanager.ui.theme.CaomanagerTheme

// Definición de rutas
sealed class Screen(val route: String) {
    object Inicio : Screen("inicio_screen")
    object Detalle : Screen("detalle_screen")
    object ListaAnimales : Screen("lista_animales_screen")
    object RegistroAnimal : Screen("registro_animal_screen")
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CaomanagerTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = Screen.Inicio.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        // Pantalla 1
                        composable(Screen.Inicio.route) {
                            MainScreen(
                                name = "Usuario",
                                onIrADetalle = { navController.navigate(Screen.Detalle.route) },
                                onIrALista = { navController.navigate(Screen.ListaAnimales.route) },
                                onIrARegistro = { navController.navigate(Screen.RegistroAnimal.route) }
                            )
                        }

                        // Pantalla 2
                        composable(Screen.Detalle.route) {
                            SegundaPantalla(onVolver = { navController.popBackStack() })
                        }

                        // Pantalla de Animales
                        composable(Screen.ListaAnimales.route) {
                            AnimalScreen(
                                onNavigateToRegistro = { navController.navigate(Screen.RegistroAnimal.route) }
                            )
                        }

                        // Pantalla de Registro de Animal
                        composable(Screen.RegistroAnimal.route) {
                            AnimalRegistroScreen(
                                onNavigateBack = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    name: String,
    modifier: Modifier = Modifier,
    onIrADetalle: () -> Unit,
    onIrALista: () -> Unit,
    onIrARegistro: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "¡Bienvenido a CaoManager, $name!")
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onIrADetalle) {
            Text(text = "Ir a la Segunda Pantalla")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onIrALista) {
            Text(text = "Ver lista de animales")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onIrARegistro) {
            Text(text = "Registrar nuevo animal")
        }
    }
}

@Composable
fun SegundaPantalla(onVolver: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "¡Lograste navegar!")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onVolver) {
            Text("Regresar")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    CaomanagerTheme {
        MainScreen("Android", onIrADetalle = {}, onIrALista = {}, onIrARegistro = {})
    }
}
