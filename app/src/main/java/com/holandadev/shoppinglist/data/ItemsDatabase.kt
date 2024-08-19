package com.holandadev.shoppinglist.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ItemEntity::class], version = 1, exportSchema = false)
abstract class ItemsDatabase: RoomDatabase() {
    abstract fun itemsDao(): ItemsDao
}