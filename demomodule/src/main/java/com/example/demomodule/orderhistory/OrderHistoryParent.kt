package com.v2px.sujin.expandables1

import android.support.v4.content.ContextCompat
import com.example.demomodule.R
import com.rosia.domain.outletDetail.OrderItem
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.view_holder_order_history_parent.view.*

class OrderHistoryParent(private var date: String? = null, var list: List<OrderItem>? = null) : Item(), ExpandableItem {
    private lateinit var expandableGroup: ExpandableGroup

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        expandableGroup = onToggleListener
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        val context = viewHolder.itemView.context
        viewHolder.itemView.orderHistoryDate.text = date
        viewHolder.itemView.setOnClickListener {
            expandableGroup.onToggleExpanded()
            if (expandableGroup.isExpanded) {
                viewHolder.itemView.orderHistoryDate.setTextColor(ContextCompat.getColor(context, R.color.azure))
            } else {
                viewHolder.itemView.orderHistoryDate.setTextColor(ContextCompat.getColor(context, R.color.primary_text_color))
            }

        }
    }

    override fun getLayout() = R.layout.view_holder_order_history_parent
}