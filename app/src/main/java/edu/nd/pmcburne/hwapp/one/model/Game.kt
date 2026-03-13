package edu.nd.pmcburne.hwapp.one.model

data class Game(
    val homeTeam: String,
    val awayTeam: String,
    val homeScore: Int?,
    val awayScore: Int?,
    val status: String,
    val period: String?,
    val clock: String?
)