package com.example.checklist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.checklist.R
import com.example.checklist.data.Chore

class CheckListAdapter (
    private val openItemDetailsActivity: (chore: Chore) -> Unit
) : androidx.recyclerview.widget.ListAdapter <Chore, CheckListViewHolder> (CheckListAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckListViewHolder {
        val view : View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.chore_checklist,parent,false)
        return CheckListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CheckListViewHolder, position: Int)  {
        val chore = getItem(position)
        holder.bind(chore, openItemDetailsActivity)
    }

    companion object : DiffUtil.ItemCallback <Chore>() {

        override fun areItemsTheSame(oldItem: Chore, newItem: Chore): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Chore, newItem: Chore): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.description == newItem.description
        }
    }
}