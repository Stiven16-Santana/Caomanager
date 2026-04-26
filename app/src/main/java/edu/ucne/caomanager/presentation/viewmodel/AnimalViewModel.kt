package edu.ucne.caomanager.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.caomanager.domain.model.Animal
import edu.ucne.caomanager.domain.model.repository.AnimalRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalViewModel @Inject constructor(
    private val repository: AnimalRepository
) : ViewModel() {

    var codigo by mutableStateOf("")
    var fechaNacimiento by mutableStateOf("")
    var fechaCompra by mutableStateOf("")
    var pesoInicial by mutableStateOf("")
    var precioCompra by mutableStateOf("")
    var estado by mutableStateOf("Activo")

    val uiState: StateFlow<List<Animal>> = repository.getAnimales()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun saveAnimal() {
        viewModelScope.launch {
            val animal = Animal(
                codigo = codigo,
                fechaNacimiento = fechaNacimiento,
                pesoInicial = pesoInicial.toDoubleOrNull() ?: 0.0,
                precioCompra = precioCompra.toDoubleOrNull() ?: 0.0,
                estado = estado,
                pesoActual = pesoInicial.toDoubleOrNull() ?: 0.0
            )
            repository.insert(animal)
            limpiarCampos()
        }
    }

    private fun limpiarCampos() {
        codigo = ""
        fechaNacimiento = ""
        fechaCompra = ""
        pesoInicial = ""
        precioCompra = ""
        estado = "Activo"
    }
}
