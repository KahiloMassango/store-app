package com.example.store.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.store.core.model.search.RecentSearch

@Entity(tableName = "recent_search")
data class RecentSearchEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val query: String,
)

fun RecentSearchEntity.asExternalModel() = RecentSearch(
    id = id,
    query = query
)
