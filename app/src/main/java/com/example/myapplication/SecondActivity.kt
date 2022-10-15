package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SecondActivity : AppCompatActivity() {
    private val databaseOperations = Operations()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        setAdapter()

        btn_save.setOnClickListener {

            val id = edt_id_save.text.toString()
            val name = edt_name_save.text.toString()
            val email = edt_email_save.text.toString()

            if (id == "") {
                Toast.makeText(
                    this@SecondActivity,
                    "Please enter id that you want to update ",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                lifecycleScope.launch(Dispatchers.IO) {
                    databaseOperations.updateData(id.toInt(), name, email)
                }
                Toast.makeText(
                    this@SecondActivity,
                    "The item has been Updated ",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setAdapter() {

        rvElement.layoutManager = LinearLayoutManager(this)
        rvElement.setHasFixedSize(true)

        //        val items: ArrayList<Person> = intent.getParcelableArrayListExtra("items")!!
//        for (i in items.indices) {
//            elementList.add(Person(items[i].id, items[i].name, items[i].email))
//        }

        // if allowQueriesOnUiThread is true
        //val realm = Realm.getDefaultInstance()
        //val realmAdapter = RealmAdapter(realm.where(PersonRealm::class.java).findAll())
        //rvElement.adapter = realmAdapter

            val retriever = databaseOperations.retrieveDataRealmObject()
                val realmAdapter = RealmAdapter(retriever)
                rvElement.adapter = realmAdapter
            }
}


