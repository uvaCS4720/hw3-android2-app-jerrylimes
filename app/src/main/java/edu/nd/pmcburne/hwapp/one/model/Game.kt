package edu.nd.pmcburne.hwapp.one.model

data class Game(
    val homeTeam: String,
    val awayTeam: String,
    val homeScore: Int?,
    val awayScore: Int?,
    val homeWinner: Boolean,
    val awayWinner: Boolean,
    val status: String,
    val period: String?,
    val clock: String?,
    val startTime: String
)