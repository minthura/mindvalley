package tech.minthura.mindvalley.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import tech.minthura.mindvalley.data.entities.DbNewEpisode

@Dao
interface EpisodeDao {

    @Insert(onConflict = REPLACE)
    fun insert(episodes: List<DbNewEpisode>)

    @Query("SELECT * FROM episodes LIMIT 6")
    fun getEpisodes(): Flow<List<DbNewEpisode>>

    @Query("DELETE FROM episodes")
    fun deleteAll()

}
