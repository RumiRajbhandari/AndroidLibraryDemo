package com.v2px.sujin.expandables1


import com.example.demomodule.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.view_holder_order_history_child.view.*

/**
 * Created by nabin on 4/18/18.
 */
class OrdersHistoryChild(var sku: String? = null, var quantity: String? = null) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.orderHistoryItemName.text = sku
        viewHolder.itemView.orderHistoryItemQty.text = quantity
        viewHolder.itemView.setOnClickListener {
            println("$sku")
        }
    }

    override fun getLayout() = R.layout.view_holder_order_history_child
}