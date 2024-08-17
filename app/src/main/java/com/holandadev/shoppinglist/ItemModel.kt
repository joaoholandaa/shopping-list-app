package com.holandadev.shoppinglist

data class ItemModel(val name: String, val onRemove: (ItemModel) -> Unit)