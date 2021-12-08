package tech.minthura.mindvalley.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import tech.minthura.mindvalley.data.daos.ChannelDao
import tech.minthura.mindvalley.data.daos.EpisodeDao
import tech.minthura.mindvalley.data.daos.MediaDao
import tech.minthura.mindvalley.data.entities.DbChannel
import tech.minthura.mindvalley.data.entities.DbMedia
import tech.minthura.mindvalley.data.entities.DbNewEpisode

@Database(entities = [DbNewEpisode::class, DbChannel::class, DbMedia::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun episodesDao() : EpisodeDao
    abstract fun channelsDao() : ChannelDao
    abstract fun mediasDao() : MediaDao
}