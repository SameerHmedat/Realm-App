package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.element_items_row.view.*

class ElementAdapter(private var ElementList: ArrayList<Person>) :
    RecyclerView.Adapter<ElementAdapter.ElementViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.element_items_row, parent, false
        )

        return ElementViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ElementViewHolder, position: Int) {
        val currentItem: Person = ElementList[position]
        holder.bind(currentItem)

    }

    override fun getItemCount(): Int {
        return ElementList.size
    }

    class ElementViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(person: Person) {
            itemView.txt_element_Id.text = person.id.toString()
            itemView.txt_element_name.text = person.name
            itemView.txt_element_email.text = person.email
        }


    }
}
