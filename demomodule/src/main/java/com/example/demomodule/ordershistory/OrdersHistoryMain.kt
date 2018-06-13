package com.rosia.orderhistory

import com.example.demomodule.R
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.view_holder_order_history_header.view.*

/**
 * Created by nabin on 4/18/18.
 */
class OrdersHistoryMain(private var months: String? = null) : Item(), ExpandableItem {

    private lateinit var expandableGroup: ExpandableGroup

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        expandableGroup = onToggleListener
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.itemType.text = months
        viewHolder.itemView.setOnClickListener {
            expandableGroup.onToggleExpanded()
        }
    }

    override fun getLayout(): Int {
        return R.layout.view_holder_order_history_header
    }
}