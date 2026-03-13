package edu.nd.pmcburne.hwapp.one.model.dto

import edu.nd.pmcburne.hwapp.one.model.Game

fun GameDto.toGame(): Game {
    return Game(
        homeTeam = home.names.short,
        awayTeam = away.names.short,
        homeScore = home.score?.toIntOrNull(),
        awayScore = away.score?.toIntOrNull(),
        status = gameState,
        period = currentPeriod,
        clock = contestClock
    )
}