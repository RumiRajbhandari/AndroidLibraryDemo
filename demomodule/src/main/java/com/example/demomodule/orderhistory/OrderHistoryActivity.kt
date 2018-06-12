package com.rosia.orderhistory

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.example.demomodule.R
import com.example.demomodule.base.BaseActivity
import com.example.demomodule.databinding.ActivityOrderHistoryBinding
import com.example.demomodule.outletDetail.formatDate
import com.example.demomodule.outletDetail.formatMonths
import com.rosia.domain.outletDetail.OrderHistory
import com.v2px.sujin.expandables1.OrderHistoryChild
import com.v2px.sujin.expandables1.OrderHistoryParent
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class OrderHistoryActivity : BaseActivity(), OrderHistoryContract.View {

    @Inject
    lateinit var presenter: OrderHistoryContract.Presenter
    private lateinit var groupAdapter: GroupAdapter<ViewHolder>
    private lateinit var binding: ActivityOrderHistoryBinding

    companion object {
        private const val KEY_OUTLET_ID = "_outletID"
        fun start(activity: Activity, outletId: Int) {
            val intent = Intent(activity, OrderHistoryActivity::class.java)
            intent.putExtra(KEY_OUTLET_ID, outletId)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = DataBindingUtil.setContentView(this@OrderHistoryActivity, R.layout.activity_order_history)
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

    override fun showOrderHistorySuccess(orderHistoryItems: List<OrderHistory>) {
        super.showData(this.binding)
        val rvBaseRecyclerView = this.binding.rvBaseOrderHistory
        groupAdapter = GroupAdapter()
        val groups = orderHistoryItems.groupBy { item ->
            formatMonths(item.date)
        }

        groups.forEach {
            ExpandableGroup(OrderHistoryMain(it.key)).apply {
                groupAdapter.add(this)
            }
            it.value.forEach {
                ExpandableGroup(OrderHistoryParent(formatDate(it.date), it.orders)).apply {
                    it.orders?.forEach {
                        add(Section(OrderHistoryChild(it.skuName, it.quantity.toString())))
                    }
                    groupAdapter.add(this)
                }
            }
        }
        //TODO
        rvBaseRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@OrderHistoryActivity)
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


