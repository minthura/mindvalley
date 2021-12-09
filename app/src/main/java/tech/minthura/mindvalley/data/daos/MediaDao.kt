package tech.minthura.mindvalley.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import tech.minthura.mindvalley.data.entities.DbMedia

@Dao
interface MediaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(channels: List<DbMedia>) : List<Long>

    @Query("DELETE FROM medias")
    fun deleteAll() : Int

}