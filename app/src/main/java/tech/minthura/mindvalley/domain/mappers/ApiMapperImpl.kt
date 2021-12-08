package tech.minthura.mindvalley.domain.mappers

import tech.minthura.mindvalley.data.entities.DbChannel
import tech.minthura.mindvalley.data.entities.DbMedia
import tech.minthura.mindvalley.data.entities.DbNewEpisode
import tech.minthura.mindvalley.domain.models.Channels
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

    override fun mapChannelToDbMedia(channel: Channels.Channel, channelId: Long): List<DbMedia> {
        val dbMedias = mutableListOf<DbMedia>()
        if(channel.series?.isEmpty() == true){
            channel.latestMedia?.let {
                for (media in it.take(6)){
                    dbMedias.add(DbMedia(media.title, media.coverAsset?.url, channelId))
                }
            }
        } else{
            channel.series?.let {
                for (media in it.take(6)){
                    dbMedias.add(DbMedia(media.title, media.coverAsset?.url, channelId))
                }
            }
        }
        return dbMedias
    }

    override fun mapChannelToDbChannel(channel: Channels.Channel): DbChannel {
        return if(channel.series?.isEmpty() == true){
            DbChannel(channel.title, channel.mediaCount, channel.iconAsset?.thumbnailUrl ?: channel.iconAsset?.url, 0) // Course
        }else {
            DbChannel(channel.title, channel.mediaCount, channel.iconAsset?.thumbnailUrl ?: channel.iconAsset?.url, 1) // Series
        }
    }

}
