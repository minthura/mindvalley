package tech.minthura.mindvalley.domain.mappers

import tech.minthura.mindvalley.data.entities.DbNewEpisode
import tech.minthura.mindvalley.domain.models.Episodes

interface ApiMapper {
    fun mapEpisodesToDbEpisodes(episodes: Episodes): List<DbNewEpisode>
}