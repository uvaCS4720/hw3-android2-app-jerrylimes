package edu.nd.pmcburne.hwapp.one.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.nd.pmcburne.hwapp.one.model.Game
import edu.nd.pmcburne.hwapp.one.model.api.RetrofitInstance
import edu.nd.pmcburne.hwapp.one.model.api.RetrofitInstance.api
import edu.nd.pmcburne.hwapp.one.model.dto.toGame
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameViewModel : ViewModel() {
    var games by mutableStateOf<List<Game>>(emptyList())
        private set

    fun loadGames() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getScoreBoard()
                Log.d("GameViewModel", "API returned ${response.games.size} games")
                games = response.games.map { it.game.toGame() }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}