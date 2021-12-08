package tech.minthura.mindvalley.domain

import tech.minthura.mindvalley.domain.models.Episodes

interface ApiHelper {
    suspend fun getEpisodes() : Episodes
}