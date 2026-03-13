package edu.nd.pmcburne.hwapp.one.model.api
import edu.nd.pmcburne.hwapp.one.model.dto.ScoreboardResponse
import kotlinx.serialization.Serializable
import retrofit2.http.GET

interface NcaaApiService {
    @GET("scoreboard/basketball-men/d1")
    suspend fun getScoreBoard(): ScoreboardResponse
}
