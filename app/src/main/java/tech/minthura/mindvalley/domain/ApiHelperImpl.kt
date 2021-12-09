package tech.minthura.mindvalley.domain

import tech.minthura.mindvalley.domain.models.Categories
import tech.minthura.mindvalley.domain.models.Channels
import tech.minthura.mindvalley.domain.models.Episodes
import tech.minthura.mindvalley.domain.services.ApiService
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getEpisodes(): Episodes {
        return apiService.getEpisodes()
    }

    override suspend fun getChannels(): Channels {
        return apiService.getChannels()
    }

    override suspend fun getCategories(): Categories {
        return apiService.getCategories()
    }
}
