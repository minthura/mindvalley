package tech.minthura.mindvalley.data.daos

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import tech.minthura.mindvalley.data.entities.ChannelWithMedias
import tech.minthura.mindvalley.data.entities.DbChannel

@Dao
interface ChannelDao {
    @Transaction
    @Query("SELECT * FROM channels")
    fun getChannelsWithMedias(): Flow<List<ChannelWithMedias>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(channel: DbChannel) : Long

    @Query("DELETE FROM channels")
    fun deleteAll()
}