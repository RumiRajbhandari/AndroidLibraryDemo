package com.rosia.orderhistory

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.example.demomodule.R
import com.example.demomodule.base.BasesActivity
import com.example.demomodule.databinding.ActivityOrderHistorysBinding
import com.example.demomodule.outletsDetail.formatDate
import com.example.demomodule.outletsDetail.formatMonths
import com.rosia.domain.outletDetail.OrdersHistory
import com.v2px.sujin.expandables1.OrdersHistoryChild
import com.v2px.sujin.expandables1.OrdersHistoryParent
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import javax.inject.Inject


class OrdersHistoryActivity : BasesActivity(), OrdersHistoryContract.View {

    @Inject
    lateinit var presenter: OrdersHistoryContract.Presenters
    private lateinit var groupAdapter: GroupAdapter<ViewHolder>
    private lateinit var binding: ActivityOrderHistorysBinding

    companion object {
        private const val KEY_OUTLET_ID = "_outletID"
        fun start(activity: Activity, outletId: Int) {
            val intent = Intent(activity, OrdersHistoryActivity::class.java)
            intent.putExtra(KEY_OUTLET_ID, outletId)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = DataBindingUtil.setContentView(this@OrdersHistoryActivity, R.layout.activity_order_historys)
        binding.toolbar.title = getString(R.string.order_history)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener({ onBackPressed() })
        presenter.start()
    }

    override fun onPause() {
        presenter.stop()
        super.onPause()

    }

    override fun getOutletId(): Int {
        return intent.getIntExtra(KEY_OUTLET_ID, 0)
    }


    override fun showError(message: String) {
        super.showError(this.binding, message)
    }

    override fun showNetworkNotAvailableError() {

    }

    override fun showOrderHistorySuccess(ordersHistoryItems: List<OrdersHistory>) {
        super.showData(this.binding)
        val rvBaseRecyclerView = this.binding.rvBaseOrderHistory
        groupAdapter = GroupAdapter()
        val groups = ordersHistoryItems.groupBy { item ->
            formatMonths(item.date)
        }

        groups.forEach {
            ExpandableGroup(OrdersHistoryMain(it.key)).apply {
                groupAdapter.add(this)
            }
            it.value.forEach {
                ExpandableGroup(OrdersHistoryParent(formatDate(it.date), it.orders)).apply {
                    it.orders?.forEach {
                        add(Section(OrdersHistoryChild(it.skuName, it.quantity.toString())))
                    }
                    groupAdapter.add(this)
                }
            }
        }
        rvBaseRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@OrdersHistoryActivity)
            adapter = groupAdapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}


