package edu.nd.pmcburne.hwapp.one.model.dto

import edu.nd.pmcburne.hwapp.one.data.local.GameEntity
import edu.nd.pmcburne.hwapp.one.model.Game

fun GameDto.toEntity(gender: String, year: String, month: String, day: String): GameEntity {
    return GameEntity(
        homeTeam = home.names.short,
        awayTeam = away.names.short,
        homeScore = home.score?.toIntOrNull(),
        awayScore = away.score?.toIntOrNull(),
        homeWinner = home.winner,
        awayWinner = away.winner,
        status = gameState,
        period = currentPeriod,
        clock = contestClock,
        startTime = startTime,
        gender = gender,
        year = year,
        month = month,
        day = day
    )
}

fun GameEntity.toDomain(): Game {
    return Game(
        homeTeam = homeTeam,
        awayTeam = awayTeam,
        homeScore = homeScore,
        awayScore = awayScore,
        homeWinner = homeWinner,
        awayWinner = awayWinner,
        status = status,
        period = period,
        clock = clock,
        startTime = startTime
    )
}