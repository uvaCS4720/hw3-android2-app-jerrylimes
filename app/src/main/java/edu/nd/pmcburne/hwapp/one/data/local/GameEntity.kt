package edu.nd.pmcburne.hwapp.one.data.local

import androidx.room.Entity

@Entity(
    tableName = "games",
    primaryKeys = ["homeTeam", "awayTeam", "startTime"]
)

data class GameEntity(
    val homeTeam: String,
    val awayTeam: String,
    val homeScore: Int?,
    val awayScore: Int?,
    val homeWinner: Boolean,
    val awayWinner: Boolean,
    val status: String,
    val period: String?,
    val clock: String?,
    val startTime: String,
    val gender: String,
    val year: String,
    val month: String,
    val day: String
)