package com.example.banking_and_finance.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.banking_and_finance.R
import com.example.banking_and_finance.data.BankDatabaseHandler
import com.example.banking_and_finance.data.BankListAdapter
import com.example.banking_and_finance.model.bank
import kotlinx.android.synthetic.main.activity_bank_list.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.popup.view.*

class BankList : AppCompatActivity() {

    private var adapter: BankListAdapter? = null
    private var joblist: ArrayList<bank>? = null
    private var jobListItems: ArrayList<bank>? = null
    private var dialogBuilder: AlertDialog.Builder? = null
    private var dialog: AlertDialog? = null

    private var layoutManager: RecyclerView.LayoutManager? = null
    var dbhandler: BankDatabaseHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank_list)

        dbhandler = BankDatabaseHandler(this)

        layoutManager = LinearLayoutManager(this)
        banklist = ArrayList<bank>()
        bankListItems = ArrayList()
        adapter = BankListAdapter(jobListItems!!, this)


        //setup list = recycler view
        recyclerviewId.layoutManager = layoutManager
        recyclerviewId.adapter = adapter

        // load our jobs
        banklist = dbhandler!!.readBanks()
        banklist!!.reverse()

        for (c in banklist!!.iterator()) {

            val bank = bank()
            bank.bankName = c.bankName
            bank.assignedTo = "${c.assignedTo}"
            bank.assignedBy = "${c.assignedBy}"
            bank.id = c.id
            bank.showHumanDate(c.timeAssigned!!)

            bankListItems!!.add(bank)
        }

        adapter!!.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.top_menu, menu) //menu object is passed here declared in overriding function
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {  //this function is used to provide and onClickListener to the add menu button

        if (item!!.itemId == R.id.add_menu_button){

            Log.d("chutiyapa", "bht bda")
            createPopupDialog()
        }

        return super.onOptionsItemSelected(item)
    }

    fun createPopupDialog(){  // instantiate dialog builder and dialog

        var view = layoutInflater.inflate(R.layout.popup, null) // this view will have our popup
        var bankName = view.popenterbankId
        var assignedBy = view.popenterallocatorId
        var assignedTo = view.popenteracceptorId
        var savebank = view.popsaveButtonId

        // instantiating dialog builder
        dialogBuilder = AlertDialog.Builder(this).setView(view)
        dialog  = dialogBuilder!!.create()
        dialog?.show()

        savebank.setOnClickListener {
            if (!TextUtils.isEmpty(bankName.text.toString().trim())
                && !TextUtils.isEmpty((assignedBy.text.toString().trim()))
                && !TextUtils.isEmpty(assignedTo.text.toString().trim())){

                var bank = bank()
                bank.bankName = bankName.text.toString().trim()
                bank.assignedBy = assignedBy.text.toString().trim()
                bank.assignedTo = assignedTo.text.toString().trim()


                dbhandler!!.createJob(bank)
                dialog!!.dismiss()

                startActivity(Intent(this, BankList :: class.java))
                finish()

            }
        }


    }
}