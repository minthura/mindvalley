package tech.minthura.mindvalley.domain.mappers

import tech.minthura.mindvalley.data.entities.DbCategory
import tech.minthura.mindvalley.data.entities.DbChannel
import tech.minthura.mindvalley.data.entities.DbMedia
import tech.minthura.mindvalley.data.entities.DbNewEpisode
import tech.minthura.mindvalley.domain.models.Categories
import tech.minthura.mindvalley.domain.models.Channels
import tech.minthura.mindvalley.domain.models.Episodes

interface ApiMapper {
    fun mapEpisodesToDbEpisodes(episodes: Episodes): List<DbNewEpisode>
    fun mapChannelToDbChannel(channel: Channels.Channel): DbChannel
    fun mapChannelToDbMedia(channel: Channels.Channel, channelId: Long): List<DbMedia>
    fun mapCategoriesToDbCategories(categories: Categories): List<DbCategory>
}