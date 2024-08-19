package com.holandadev.shoppinglist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.holandadev.shoppinglist.data.ItemEntity
import com.holandadev.shoppinglist.data.ItemsDatabase
import com.holandadev.shoppinglist.data.toModel
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
            val entity = ItemEntity(id = 0, name = name)
            database.itemsDao().insert(entity)
            fetchAll()}
    }

    private suspend fun fetchAll() {
        val result = database.itemsDao().getAll().map {
            it.toModel(onRemove = {})
        }
        itemsLiveData.postValue(result)
    }
}