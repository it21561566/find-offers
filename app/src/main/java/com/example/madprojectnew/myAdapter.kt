package com.example.madprojectnew
import android.view.TextureView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class myAdapter(var discountList:ArrayList<datalistDisc>):RecyclerView.Adapter<myAdapter.MyViewHolder>() {

    private lateinit var mListner: onItemClickListner

    interface onItemClickListner{
        fun onItemClick(position: Int)
    }

    fun onItemClickListner(listner: onItemClickListner){
        mListner=listner
    }

    class MyViewHolder(itemView:View,listner: onItemClickListner):RecyclerView.ViewHolder(itemView){
        val startDate:TextView=itemView.findViewById(R.id.textView7)
        val endDate:TextView=itemView.findViewById(R.id.textView13)
        val discount:TextView=itemView.findViewById(R.id.textView14)
        val description:TextView=itemView.findViewById(R.id.textView15)

        init {
            itemView.setOnClickListener{
                listner.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =LayoutInflater.from(parent.context).inflate(R.layout.profile2,parent,false)
        return MyViewHolder(itemView,mListner)
    }
    override fun getItemCount(): Int {
        return discountList.size
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = discountList[position]
        holder.startDate.text=currentItem.startDate
        holder.endDate.text=currentItem.endDate
        holder.discount.text=currentItem.discount
        holder.description.text=currentItem.description
    }
}