package tech.minthura.mindvalley.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class DbCategory(
    val name: String?,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)