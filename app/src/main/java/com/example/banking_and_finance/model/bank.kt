package com.example.banking_and_finance.model

import java.text.DateFormat
import java.util.*

class bank() {

    var bankName: String? = null
    var assignedBy: String? = null
    var assignedTo: String? = null
    var timeAssigned: Long? = null
    var id: Int? = null


    constructor(bankName: String, assignedBy: String, assignedTo: String,
                timeAssigned: Long, id: Int): this() {

        this.bankName = bankName
        this.assignedBy = assignedBy
        this.assignedTo = assignedTo
        this.timeAssigned = timeAssigned
        this.id = id
    }

    fun showHumanDate(timeAssigned: Long): String {

        var dateFormat: java.text.DateFormat = DateFormat.getDateInstance()
        var formattedDate: String = dateFormat.format(Date(timeAssigned).time)

        return "created on  ${formattedDate}"
    }}