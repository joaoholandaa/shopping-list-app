package com.holandadev.shoppinglist.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.holandadev.shoppinglist.ItemModel

@Entity
class ItemEntity {
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String
}

fun ItemEntity.toModel(onRemove: (ItemModel) -> Unit): ItemModel {
    return ItemModel(
        name = this.name,
        onRemove = onRemove
    )
}