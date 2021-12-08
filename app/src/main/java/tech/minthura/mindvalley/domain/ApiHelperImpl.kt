package tech.minthura.mindvalley.domain

import tech.minthura.mindvalley.domain.models.Episodes
import tech.minthura.mindvalley.domain.services.ApiService
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getEpisodes(): Episodes {
        return apiService.getEpisodes()
    }
}
