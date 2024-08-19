package com.holandadev.shoppinglist.data

import android.content.ClipData.Item
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemsDao {
    @Insert
    suspend fun insert(item: ItemEntity): Long

    @Delete
    suspend fun delete(item: ItemEntity): Int

    @Query("select * from items")
    suspend fun getAll(): List<ItemEntity>
}