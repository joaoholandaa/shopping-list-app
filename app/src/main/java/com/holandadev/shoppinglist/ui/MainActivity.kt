package com.holandadev.shoppinglist.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.holandadev.shoppinglist.viewmodel.ItemsViewModel
import com.holandadev.shoppinglist.viewmodel.ItemsViewModelFactory
import com.holandadev.shoppinglist.R

class MainActivity : AppCompatActivity() {

    val viewModel: ItemsViewModel by viewModels {
        ItemsViewModelFactory(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val itemsAdapter = ItemsAdapter()
        recyclerView.adapter = itemsAdapter

        val button = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.editText)
        button.setOnClickListener {
            if (editText.text.isEmpty()) {
                editText.error = "Fill in the field with a value"
                return@setOnClickListener
            }

            viewModel.addItem(editText.text.toString())

            editText.text.clear()
        }

        viewModel.itemsLiveData.observe(this) { items ->
            itemsAdapter.updateItems(items)
        }
    }
}