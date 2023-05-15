package com.example.findoffers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class shopAdapter(var shopTypeList: ArrayList<shoptype>): RecyclerView.Adapter<shopAdapter.ViewHolder>()  {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val shopName: TextView = itemView.findViewById(R.id.textView5)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.shop_type_list,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = shopTypeList[position]
        holder.shopName.text = currentItem.ShopName
    }

    override fun getItemCount(): Int {
        return shopTypeList.size
    }


}