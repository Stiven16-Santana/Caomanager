package edu.ucne.caomanager.presentation.animal

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import edu.ucne.caomanager.presentation.viewmodel.AnimalViewModel

@Composable
fun AnimalScreen(
    viewModel: AnimalViewModel = hiltViewModel()
) {

    val listaAnimales by viewModel.uiState.collectAsState()


    LazyColumn {
        items(listaAnimales) { animal ->
            Text(text = "Código: ${animal.codigo} | Estado: ${animal.estado}")
        }
    }
}