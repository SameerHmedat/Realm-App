package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.element_items_row.view.*

class ElementAdapter(private var ElementList: List<Element>) :
    RecyclerView.Adapter<ElementAdapter.ElementViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.element_items_row, parent, false
        )

        return ElementViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ElementViewHolder, position: Int) {
        val currentItem: Element = ElementList[position]
        holder.bind(currentItem)

    }

    override fun getItemCount(): Int {
        return ElementList.size
    }

    class ElementViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(element: Element) {
            itemView.txt_element_Id.text = element.id.toString()
            itemView.txt_element_name.text = element.name
            itemView.txt_element_email.text = element.email
        }


    }
}
