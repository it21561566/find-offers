package com.example.banking_and_finance.activity

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.banking_and_finance.R
import com.example.banking_and_finance.data.BankDatabaseHandler
import com.example.banking_and_finance.data.BankListAdapter
import com.example.banking_and_finance.model.bank
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var dbhandler: BankDatabaseHandler? = null
    var progressBar: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = ProgressDialog(this)

        dbhandler = BankDatabaseHandler(this)

        checkDB()



        saveButton.setOnClickListener {
            progressBar!!.setMessage("saving...")
            progressBar!!.show()

            if (!TextUtils.isEmpty(bankId.text.toString()) && !TextUtils.isEmpty(allocatorId.text.toString()) &&
                !TextUtils.isEmpty(acceptorId.text.toString())){

                // save to database

                var job: bank =  bank()
                job.bankName = bankId.text.toString()
                job.assignedTo = acceptorId.text.toString()
                job.assignedBy = allocatorId.text.toString()

                saveTODB(job)
                progressBar!!.cancel()
                startActivity(Intent(this, BankList:: class.java))

            }
            else {
                Toast.makeText(this, "Please enter the information", Toast.LENGTH_LONG).show()
            }
        }

    }

    fun saveTODB(job: bank){
        dbhandler!!.createJob(job)
    }

    fun checkDB(){ //checking if database has some data then it will redirect user to second job list page
        if (dbhandler!!.getJobsCount() > 0)
            startActivity(Intent(this, BankList :: class.java))

    }
}
