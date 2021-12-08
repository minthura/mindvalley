package tech.minthura.mindvalley.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "channels")
data class DbChannel(
    val title: String?,
    val mediaCount: Int?,
    val iconAsset: String?,
    val type: Int?,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)

@Entity(tableName = "medias")
data class DbMedia(
    val title: String?,
    val assetUrl: String?,
    val channelId: Long,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)

data class ChannelWithMedias(
    @Embedded val dbChannel: DbChannel,
    @Relation(
        parentColumn = "id",
        entityColumn = "channelId"
    )
    val dbMedia: List<DbMedia>
)