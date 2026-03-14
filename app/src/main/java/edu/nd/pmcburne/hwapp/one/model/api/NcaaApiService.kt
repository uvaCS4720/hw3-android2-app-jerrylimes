package edu.nd.pmcburne.hwapp.one.model.api
import edu.nd.pmcburne.hwapp.one.model.dto.ScoreboardResponse
import kotlinx.serialization.Serializable
import retrofit2.http.GET
import retrofit2.http.Path

interface NcaaApiService {
    @GET("scoreboard/basketball-{gender}/d1/{year}/{month}/{day}")
    suspend fun getScoreBoard(
        @Path("gender") gender: String,
        @Path("year") year: String,
        @Path("month") month: String,
        @Path("day") day: String
    ): ScoreboardResponse
}
