package com.holandadev.shoppinglist

import com.holandadev.shoppinglist.data.ItemEntity

data class ItemModel(
    val id: Long,
    val name: String,
    val onRemove: (ItemModel) -> Unit
)

fun ItemModel.toEntity(): ItemEntity {
    return ItemEntity(id = this.id, name = this.name)
}