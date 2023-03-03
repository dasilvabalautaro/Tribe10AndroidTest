package com.globalhiddenodds.tribe10androidtest.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "histories")
class History(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val description: String,
    val image: ByteArray?
)