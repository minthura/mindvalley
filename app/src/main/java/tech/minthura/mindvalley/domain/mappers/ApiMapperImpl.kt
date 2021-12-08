package tech.minthura.mindvalley.domain.mappers

import tech.minthura.mindvalley.data.entities.DbNewEpisode
import tech.minthura.mindvalley.domain.models.Episodes
import javax.inject.Inject

class ApiMapperImpl @Inject constructor() : ApiMapper {
    override fun mapEpisodesToDbEpisodes(episodes: Episodes): MutableList<DbNewEpisode> {
        val dbNewEpisodes = mutableListOf<DbNewEpisode>()
        episodes.data?.let { data ->
            data.media?.let {
                for (media in it){
                    dbNewEpisodes.add(DbNewEpisode(media.title, media.coverAsset?.url, media.channel?.title))
                }
            }
        }
        return dbNewEpisodes
    }
}
