package edu.nd.pmcburne.hwapp.one.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class ScoreboardResponse(
    val games: List<GameWrapperDto> = emptyList()
)

@Serializable
data class GameWrapperDto(
    val game: GameDto
)

@Serializable
data class GameDto(
    val home: TeamDto,
    val away: TeamDto,
    val gameState: String,
    val startTime: String,
    val currentPeriod: String,
    val contestClock: String,
    val finalMessage: String?
)

@Serializable
data class TeamDto(
    val score: String?, val names: TeamNamesDto, val winner: Boolean
)

@Serializable
data class TeamNamesDto(
    val short: String
)