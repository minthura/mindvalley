package tech.minthura.mindvalley.domain


import tech.minthura.mindvalley.domain.models.Callback
import tech.minthura.mindvalley.domain.models.Episodes

interface Repository  {
    suspend fun getEpisodes(callback : Callback<Episodes>)
}