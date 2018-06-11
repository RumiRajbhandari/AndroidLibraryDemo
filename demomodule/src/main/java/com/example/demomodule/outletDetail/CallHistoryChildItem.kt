package com.rosia.outletdetail

import com.example.demomodule.R
import com.example.demomodule.outletDetail.formatMilliSecondToMonthAndDay
import com.rosia.domain.outletDetail.CallHistory
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_call_history_child.view.*
import java.text.DecimalFormat


class CallHistoryChildItem(private val callHistory: CallHistory) : Item(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
        val cHistory = DecimalFormat("#.##").format(callHistory.amount)
        viewHolder.itemView.tv_date.text = formatMilliSecondToMonthAndDay(callHistory.date)
        viewHolder.itemView.tv_amount.text = if(cHistory == "0") "" else cHistory
        viewHolder.itemView.tv_status.text = callHistory.status
    }

    override fun getLayout(): Int {
       return R.layout.item_call_history_child
    }

}