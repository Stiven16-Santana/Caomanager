package edu.ucne.caomanager.presentation.animal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import edu.ucne.caomanager.domain.model.Animal
import edu.ucne.caomanager.presentation.viewmodel.AnimalViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimalRegistroScreen(
    viewModel: AnimalViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    var showNacimientoPicker by remember { mutableStateOf(false) }
    var showCompraPicker by remember { mutableStateOf(false) }
    var showSearchDialog by remember { mutableStateOf(false) }
    
    val nacimientoPickerState = rememberDatePickerState()
    val compraPickerState = rememberDatePickerState()
    
    val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val listaAnimales by viewModel.uiState.collectAsState()

    // Diálogo de Búsqueda
    if (showSearchDialog) {
        AlertDialog(
            onDismissRequest = { showSearchDialog = false },
            title = { Text("Seleccionar Animal") },
            text = {
                if (listaAnimales.isEmpty()) {
                    Text("No hay animales registrados.")
                } else {
                    Box(modifier = Modifier.heightIn(max = 400.dp)) {
                        LazyColumn {
                            items(listaAnimales) { animal ->
                                ListItem(
                                    headlineContent = { Text("Arete: ${animal.codigo}") },
                                    supportingContent = { Text("Estado: ${animal.estado}") },
                                    modifier = Modifier.clickable {
                                        viewModel.seleccionarAnimal(animal)
                                        showSearchDialog = false
                                    }
                                )
                                HorizontalDivider()
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showSearchDialog = false }) {
                    Text("Cerrar")
                }
            }
        )
    }

    // DatePicker para Fecha de Nacimiento
    if (showNacimientoPicker) {
        DatePickerDialog(
            onDismissRequest = { showNacimientoPicker = false },
            confirmButton = {
                TextButton(onClick = {
                    nacimientoPickerState.selectedDateMillis?.let { millis ->
                        viewModel.fechaNacimiento = dateFormatter.format(Date(millis))
                    }
                    showNacimientoPicker = false
                }) { Text("Aceptar") }
            },
            dismissButton = {
                TextButton(onClick = { showNacimientoPicker = false }) { Text("Cancelar") }
            }
        ) { DatePicker(state = nacimientoPickerState) }
    }

    // DatePicker para Fecha de Compra
    if (showCompraPicker) {
        DatePickerDialog(
            onDismissRequest = { showCompraPicker = false },
            confirmButton = {
                TextButton(onClick = {
                    compraPickerState.selectedDateMillis?.let { millis ->
                        viewModel.fechaCompra = dateFormatter.format(Date(millis))
                    }
                    showCompraPicker = false
                }) { Text("Aceptar") }
            },
            dismissButton = {
                TextButton(onClick = { showCompraPicker = false }) { Text("Cancelar") }
            }
        ) { DatePicker(state = compraPickerState) }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(if (viewModel.id == 0) "Registro de Animal" else "Modificar Animal") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                },
                actions = {
                    if (viewModel.id != 0) {
                        IconButton(onClick = { viewModel.limpiarCampos() }) {
                            Icon(Icons.Default.Add, contentDescription = "Nuevo")
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Campo Código con lupa de búsqueda
            OutlinedTextField(
                value = viewModel.codigo,
                onValueChange = { viewModel.codigo = it },
                label = { Text("Código (Arete)") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.QrCode, contentDescription = null) },
                trailingIcon = {
                    IconButton(onClick = { showSearchDialog = true }) {
                        Icon(Icons.Default.Search, contentDescription = "Buscar animal")
                    }
                }
            )

            // Campo Fecha de Compra con DatePicker
            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = viewModel.fechaCompra,
                    onValueChange = { },
                    label = { Text("Fecha de Compra") },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    leadingIcon = { Icon(Icons.Default.CalendarToday, contentDescription = null) },
                    trailingIcon = {
                        IconButton(onClick = { showCompraPicker = true }) {
                            Icon(Icons.Default.DateRange, contentDescription = "Seleccionar fecha compra")
                        }
                    }
                )
                Box(modifier = Modifier.matchParentSize().clickable { showCompraPicker = true })
            }

            // Campo Peso Inicial
            OutlinedTextField(
                value = viewModel.pesoInicial,
                onValueChange = { viewModel.pesoInicial = it },
                label = { Text("Peso Inicial (kg)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                leadingIcon = { Icon(Icons.Default.Scale, contentDescription = null) }
            )

            // Campo Fecha de Nacimiento con DatePicker
            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = viewModel.fechaNacimiento,
                    onValueChange = { },
                    label = { Text("Fecha de Nacimiento") },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    leadingIcon = { Icon(Icons.Default.CalendarMonth, contentDescription = null) },
                    trailingIcon = {
                        IconButton(onClick = { showNacimientoPicker = true }) {
                            Icon(Icons.Default.DateRange, contentDescription = "Seleccionar fecha nacimiento")
                        }
                    }
                )
                Box(modifier = Modifier.matchParentSize().clickable { showNacimientoPicker = true })
            }

            // Campo Precio de Compra
            OutlinedTextField(
                value = viewModel.precioCompra,
                onValueChange = { viewModel.precioCompra = it },
                label = { Text("Precio de Compra") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                leadingIcon = { Icon(Icons.Default.AttachMoney, contentDescription = null) }
            )

            // Campo Estado
            OutlinedTextField(
                value = viewModel.estado,
                onValueChange = { viewModel.estado = it },
                label = { Text("Estado") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.Info, contentDescription = null) }
            )

            // Campo Observación
            OutlinedTextField(
                value = viewModel.observacion,
                onValueChange = { viewModel.observacion = it },
                label = { Text("Observación") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.Notes, contentDescription = null) },
                minLines = 3
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.saveAnimal()
                    onNavigateBack()
                },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            ) {
                Icon(if (viewModel.id == 0) Icons.Default.Save else Icons.Default.Edit, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text(if (viewModel.id == 0) "Guardar Animal" else "Modificar Animal")
            }
        }
    }
}
