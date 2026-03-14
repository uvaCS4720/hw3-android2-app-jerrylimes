package edu.nd.pmcburne.hwapp.one.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Upsert
    suspend fun upsertGames(games: List<GameEntity>)

    @Query("SELECT * FROM games WHERE gender = :gender AND year = :year AND month = :month AND day = :day")
    fun getGames(gender: String, year: String, month: String, day: String): Flow<List<GameEntity>>
}