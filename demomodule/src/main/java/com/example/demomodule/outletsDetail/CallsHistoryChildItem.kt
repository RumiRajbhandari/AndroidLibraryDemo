package com.rosia.outletdetail

import com.example.demomodule.R
import com.example.demomodule.outletsDetail.formatMilliSecondToMonthAndDay
import com.rosia.domain.outletDetail.CallsHistory
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_call_history_child.view.*
import java.text.DecimalFormat


class CallsHistoryChildItem(private val callsHistory: CallsHistory) : Item(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
        val cHistory = DecimalFormat("#.##").format(callsHistory.amount)
        viewHolder.itemView.tv_date.text = formatMilliSecondToMonthAndDay(callsHistory.date)
        viewHolder.itemView.tv_amount.text = if(cHistory == "0") "" else cHistory
        viewHolder.itemView.tv_status.text = callsHistory.status
    }

    override fun getLayout(): Int {
       return R.layout.item_call_history_child
    }

}