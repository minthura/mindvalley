package tech.minthura.mindvalley.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import tech.minthura.mindvalley.data.daos.EpisodeDao
import tech.minthura.mindvalley.data.entities.DbNewEpisode

@Database(entities = [DbNewEpisode::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun episodesDao() : EpisodeDao
}