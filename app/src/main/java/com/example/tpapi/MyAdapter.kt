package com.example.tpapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val context: Context, val userList: List<MyDataItem>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var id: TextView
        var title: TextView


        init {
            id = itemView.findViewById(R.id.tv_id)
            title = itemView.findViewById(R.id.tv_title)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = userList[position].userId.toString()
        holder.title.text = userList[position].title.toString()
    }
}