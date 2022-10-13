package com.example.myapplication

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.data_input.*
import kotlinx.coroutines.*
import java.util.function.LongFunction


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val databaseOperations = Operations()

        btn_insertdata.setOnClickListener {
            val name = edt_name.text.toString()
            val email = edt_email.text.toString()

            lifecycleScope.launch(Dispatchers.IO) {
                databaseOperations.insertData(name, email)
            }

            edt_id.setText("")
            edt_name.setText("")
            edt_email.setText("")
        }
        btn_readData.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val items = databaseOperations.retrieveData()
                withContext(Dispatchers.Main) {
                    if (items.lastIndex == -1) {
                        Toast.makeText(
                            this@MainActivity,
                            "The DB is empty please insert an item and try again",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "The Last item in DB",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        txt1.text =
                            "Id= ${items[0].id} \nname =${items[0].name} \nemail =${items[0].email}"
                    }
                }
            }
        }
        btn_updatedata.setOnClickListener {
            txt1.text = ""
            val id = edt_id.text.toString()
            val name = edt_name.text.toString()
            val email = edt_email.text.toString()
            if (id == "") {
                Toast.makeText(
                    this@MainActivity,
                    "Please enter id that you want to update ",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                lifecycleScope.launch(Dispatchers.IO) {
                    databaseOperations.updateData(id.toInt(), name, email)
                }
                Toast.makeText(
                    this@MainActivity,
                    "The item has been Updated ",
                    Toast.LENGTH_SHORT
                ).show()
                edt_id.setText("")
                edt_name.setText("")
                edt_email.setText("")
                txt1.text = ""

            }
        }
        btn_deletedata.setOnClickListener {
            txt1.text = ""
            val id = edt_id.text.toString()
            if (id == "") {
                Toast.makeText(
                    this@MainActivity,
                    "Please enter id that you want to delete ",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                lifecycleScope.launch(Dispatchers.IO) {
                    databaseOperations.removeData(id.toInt())
                }

                edt_id.setText("")
                txt1.text = ""
            }
        }

        btn_go_to_data.setOnClickListener {
            txt1.text = ""

            lifecycleScope.launch(Dispatchers.IO) {
                val items = databaseOperations.retrieveData()
                withContext(Dispatchers.Main) {
                    if (items.lastIndex == -1) {
                        Toast.makeText(this@MainActivity, "The DB is empty", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        val intent = Intent(this@MainActivity, SecondActivity::class.java)
//                        intent.putParcelableArrayListExtra("items", items)
                        startActivity(intent)
                    }
                }
            }
        }
    }

}
