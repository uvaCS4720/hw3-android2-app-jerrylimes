package edu.nd.pmcburne.hwapp.one.data.repository

import android.util.Log
import edu.nd.pmcburne.hwapp.one.data.local.GameDao
import edu.nd.pmcburne.hwapp.one.model.Game
import edu.nd.pmcburne.hwapp.one.model.api.NcaaApiService
import edu.nd.pmcburne.hwapp.one.model.dto.toDomain
import edu.nd.pmcburne.hwapp.one.model.dto.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepository(
    private val api: NcaaApiService,
    private val dao: GameDao
) {
    fun getGames(gender: String, year: String, month: String, day: String): Flow<List<Game>> {
        return dao.getGames(gender, year, month, day).map { entities ->
            entities.map { it.toDomain() }
        }
    }
    suspend fun refreshGames(gender: String, year: String, month: String, day: String) {
        try {
            val response = api.getScoreBoard(gender, year, month, day)
            val entities = response.games.map {
                it.game.toEntity(gender, year, month, day)
            }
            dao.upsertGames(entities)
        } catch (e: Exception) {
            Log.e("Repository", "Error loading games: ${e.message}")
        }
    }
}