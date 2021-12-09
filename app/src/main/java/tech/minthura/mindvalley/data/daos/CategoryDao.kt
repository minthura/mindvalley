package tech.minthura.mindvalley.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import tech.minthura.mindvalley.data.entities.DbCategory
import tech.minthura.mindvalley.data.entities.DbMedia
import tech.minthura.mindvalley.data.entities.DbNewEpisode

@Dao
interface CategoryDao {

    @Query("SELECT * FROM categories")
    fun getCategories(): Flow<List<DbCategory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(channels: List<DbCategory>)

    @Query("DELETE FROM categories")
    fun deleteAll()

}