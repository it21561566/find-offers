package com.example.banking_and_finance.data

import android.app.AlertDialog
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_kotlin_bank_offers_app.model.bank
import com.example.banking_and_finance.R
import com.example.banking_and_finance.model.bank
import kotlinx.android.synthetic.main.popup.view.*

class BankListAdapter(private val list: ArrayList<bank>,
                      private val context: Context): RecyclerView.Adapter<BankListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {

        //create a view from out xml file (list_row file)

        val view = LayoutInflater.from(context)
               .inflate(R.layout.list_row, parent, false)

        return ViewHolder(view, context, list)

    }

    override fun getItemCount(): Int {

        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){

        holder.bindView(list[position])

    }
        // class made inner to invoke certain functions
    inner class ViewHolder(itemView: View, context: Context, list: ArrayList<bank>): RecyclerView.ViewHolder(itemView) , View.OnClickListener {

        var mContext = context
        var mList = list

        var bankName = itemView.findViewById(R.id.ListBankName) as TextView
        var assignedBy = itemView.findViewById(R.id.ListAssignedBy) as TextView
        var assignedDate = itemView.findViewById(R.id.ListDate)    as TextView
        var assignedTo = itemView.findViewById(R.id.ListAssignedTo) as TextView
        var deleteButton = itemView.findViewById(R.id.DeleteButton) as Button
        var editButton = itemView.findViewById(R.id.EditButton) as Button


        fun bindView(bank: bank){

            bankName.text = bank.bankName
            assignedBy.text = bank.assignedBy
            assignedTo.text = bank.assignedTo
            assignedDate.text = bank.showHumanDate(System.currentTimeMillis())
            deleteButton.setOnClickListener(this)
            editButton.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            var mPosition: Int = adapterPosition
            var  bank = mList[mPosition]


            when(v!!.id) {
                deleteButton.id -> {
                    deleteBank(bank.id!!)
                    mList.removeAt(adapterPosition)
                    notifyItemRemoved(adapterPosition)

                }

                editButton.id -> {

                    editBank(bank)

                }
            }

        }

        fun deleteBank(id: Int) {

            var db: BankDatabaseHandler = BankDatabaseHandler(mContext)
            db.deletebank(id)

        }

        fun editBank(bank: bank) {

            var dialogBuilder: AlertDialog.Builder?
            var dialog: AlertDialog?
            var dbHandler: BankDatabaseHandler = BankDatabaseHandler(context)

            var view = LayoutInflater.from(context).inflate(R.layout.popup, null)
            var BankName = view.popenterbankId
            var assignedBy = view.popenterallocatorId
            var assignedTo = view.popenteracceptorId
            var saveButton = view.popsaveButtonId

            dialogBuilder = AlertDialog.Builder(context).setView(view)
            dialog = dialogBuilder!!.create()
            dialog?.show()

            saveButton.setOnClickListener {
                var name = BankName.text.toString().trim()
                var aBy =  assignedBy.text.toString().trim()
                var aTo = assignedTo.text.toString().trim()

                if (!TextUtils.isEmpty(name)
                    && !TextUtils.isEmpty(aBy)
                    && !TextUtils.isEmpty(aTo)) {
                    // var chore = Chore()

                    bank.bankName = name
                    bank.assignedTo = aTo
                    bank.assignedBy = aBy

                    dbHandler!!.updatebank(bank)
                    notifyItemChanged(adapterPosition, bank)


                    dialog!!.dismiss()


                }
        }
    }
 }
}


