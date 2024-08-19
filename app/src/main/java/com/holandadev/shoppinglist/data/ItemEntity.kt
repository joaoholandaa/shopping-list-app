package com.holandadev.shoppinglist.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.holandadev.shoppinglist.model.ItemModel

@Entity(tableName = "items")
data class ItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val name: String
)

fun ItemEntity.toModel(onRemove: (ItemModel) -> Unit): ItemModel {
    return ItemModel(
        id = this.id,
        name = this.name,
        onRemove = onRemove
    )
}