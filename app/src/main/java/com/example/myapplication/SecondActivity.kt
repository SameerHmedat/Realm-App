package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.Sort
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        setUpUIList()
        setElementList()

    }

    private fun setElementList() {
        val databaseOperations = Operations()

        val elementList: ArrayList<Person> = arrayListOf()
        val elementAdapter = ElementAdapter(elementList)

        lifecycleScope.launch(Dispatchers.IO) {
            val items = databaseOperations.retrieveData()
            withContext(Dispatchers.Main) {
                if (items.lastIndex == -1) {
                    Toast.makeText(this@SecondActivity, "The DB is empty", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    for (i in items.indices) {
                        elementList.add(Person(items[i].id, items[i].name, items[i].email))
                    }
                    rvElement.adapter = elementAdapter
                }
            }
        }

//        val items: ArrayList<Person> = intent.getParcelableArrayListExtra("items")!!

    }

    private fun setUpUIList() {
        rvElement.layoutManager = LinearLayoutManager(this)
        rvElement.setHasFixedSize(true)
    }

}