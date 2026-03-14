package edu.nd.pmcburne.hwapp.one.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.nd.pmcburne.hwapp.one.data.repository.GameRepository
import edu.nd.pmcburne.hwapp.one.model.Game
import edu.nd.pmcburne.hwapp.one.model.api.RetrofitInstance
import edu.nd.pmcburne.hwapp.one.model.api.RetrofitInstance.api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.Date

class GameViewModel(private val repository: GameRepository) : ViewModel() {
    var isLoading by mutableStateOf(false)
        private set

    val games = snapshotFlow { Pair(selectedGender, selectedDate) }
        .flatMapLatest { (gender, date) ->
            val calendar = Calendar.getInstance().apply { time = date }
            val year = calendar.get(Calendar.YEAR).toString()
            val month = (calendar.get(Calendar.MONTH) + 1).toString().padStart(2, '0')
            val day = calendar.get(Calendar.DAY_OF_MONTH).toString().padStart(2, '0')
            repository.getGames(gender, year, month, day)
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    var selectedGender by mutableStateOf("men")
    var selectedDate by mutableStateOf(Date())

    fun updateGender(isFemale: Boolean) {
        selectedGender = if (isFemale) "women" else "men"
        loadGames()
    }

    fun updateDate(date: Date) {
        selectedDate = date
        loadGames()
    }

    fun loadGames() {
        val calendar = Calendar.getInstance().apply { time = selectedDate }
        val year = calendar.get(Calendar.YEAR).toString()
        val month = (calendar.get(Calendar.MONTH) + 1).toString().padStart(2, '0')
        val day = calendar.get(Calendar.DAY_OF_MONTH).toString().padStart(2, '0')
        viewModelScope.launch {
            isLoading = true
            try {
                repository.refreshGames(selectedGender, year, month, day)
            } finally {
                isLoading = false
            }
        }
    }
}