package com.holandadev.shoppinglist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.holandadev.shoppinglist.data.ItemEntity
import com.holandadev.shoppinglist.data.ItemsDatabase
import com.holandadev.shoppinglist.data.toModel
import com.holandadev.shoppinglist.model.ItemModel
import com.holandadev.shoppinglist.model.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemsViewModel(private val database: ItemsDatabase): ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            fetchAll()
        }
    }

    val itemsLiveData = MutableLiveData<List<ItemModel>>()

    fun addItem(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val entity = ItemEntity(name = name)
            database.itemsDao().insert(entity)
            fetchAll()}
    }

    private suspend fun fetchAll() {
        val result = database.itemsDao().getAll().map {
            it.toModel(onRemove = ::removeItem)
        }
        itemsLiveData.postValue(result)
    }

    private fun removeItem(item: ItemModel) {
        viewModelScope.launch {
            val entity = item.toEntity()
            database.itemsDao().delete(entity)
            fetchAll()
        }
    }
}