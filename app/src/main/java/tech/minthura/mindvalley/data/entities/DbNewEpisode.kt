package tech.minthura.mindvalley.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episodes")
data class DbNewEpisode(
    val title: String?,
    val assetUrl: String?,
    val channelTitle: String?,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)