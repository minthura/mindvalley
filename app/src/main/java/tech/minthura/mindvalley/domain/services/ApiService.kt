package tech.minthura.mindvalley.domain.services

import retrofit2.http.GET
import tech.minthura.mindvalley.domain.models.Categories
import tech.minthura.mindvalley.domain.models.Channels
import tech.minthura.mindvalley.domain.models.Episodes

interface ApiService {
    @GET("z5AExTtw")
    suspend fun getEpisodes() : Episodes

    @GET("Xt12uVhM")
    suspend fun getChannels() : Channels

    @GET("A0CgArX3")
    suspend fun getCategories() : Categories

}