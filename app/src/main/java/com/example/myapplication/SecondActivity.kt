package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        setUpUIList()
        setElementList()

    }

    private fun setElementList() {
        val elementList: ArrayList<Element> = arrayListOf()
        val elementAdapter = ElementAdapter(elementList)

        val items: ArrayList<Person> = intent.getParcelableArrayListExtra("items")!!

        for (i in items.indices){
            elementList.add(Element(items[i].id,items[i].name,items[i].email))
        }


        rvElement.adapter = elementAdapter
    }

    private fun setUpUIList() {
        rvElement.layoutManager = LinearLayoutManager(this)
        rvElement.setHasFixedSize(true)
    }

}