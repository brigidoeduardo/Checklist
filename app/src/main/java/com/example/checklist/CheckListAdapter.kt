package com.example.checklist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil

class CheckListAdapter (
    private val openItemDetailsActivity: (item: Item) -> Unit
) : androidx.recyclerview.widget.ListAdapter <Item, CheckListViewHolder> (CheckListAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckListViewHolder {
        val view : View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_checklist,parent,false)
        return CheckListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CheckListViewHolder, position: Int)  {
        val item = getItem(position)
        holder.bind(item, openItemDetailsActivity)
    }

    companion object : DiffUtil.ItemCallback <Item>() {

        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.description == newItem.description
        }
    }
}