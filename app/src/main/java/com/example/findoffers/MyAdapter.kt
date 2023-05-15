package com.example.findoffers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(var shopList: ArrayList<DataList>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    class MyViewHolder(itemView: View, listener: OnItemClickListener): RecyclerView.ViewHolder(itemView) {
        val shopName: TextView = itemView.findViewById(R.id.textView)
        val shopCity: TextView= itemView.findViewById(R.id.textView2)
        val shopType: TextView= itemView.findViewById(R.id.textView3)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.shop_list_item,parent,false)
        return MyViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = shopList[position]
        holder.shopName.text = currentItem.ShopName
        holder.shopCity.text = currentItem. ShopCity
        holder.shopType.text = currentItem.ShopType
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

}