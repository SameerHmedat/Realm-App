package com.example.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import kotlinx.android.synthetic.main.element_items_row.view.*

internal class RealmAdapter(data: OrderedRealmCollection<PersonRealm?>?) :
    RealmRecyclerViewAdapter<PersonRealm?, RealmAdapter.RealmViewHolder?>(data, true) {

    val TAG="MainActivity"

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RealmViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.element_items_row, parent, false
        )

        return RealmViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RealmViewHolder, position: Int) {
        val obj = getItem(position)
        Log.i(TAG, "Binding view holder: ${obj!!.name}")
        holder.bind(obj)

    }

    override fun getItemId(index: Int): Long {
        return getItem(index)!!.id.toLong()
    }

    internal inner class RealmViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(person: PersonRealm?) {
            itemView.txt_element_Id.text = person?.id.toString()
            itemView.txt_element_name.text = person?.name
            itemView.txt_element_email.text = person?.email
        }

    }
    init {
        Log.i(TAG,
            "Created RealmRecyclerViewAdapter for ${getData()!!.size} items.")
    }

}