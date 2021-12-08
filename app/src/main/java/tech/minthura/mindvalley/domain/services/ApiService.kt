package tech.minthura.mindvalley.domain.services

import retrofit2.http.GET
import tech.minthura.mindvalley.domain.models.Episodes

interface ApiService {
    @GET("z5AExTtw")
    suspend fun getEpisodes() : Episodes
}