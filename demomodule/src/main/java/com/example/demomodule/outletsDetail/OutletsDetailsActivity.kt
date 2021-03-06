package com.rosia.outletdetail

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.demomodule.FabButtonClickListener
import com.example.demomodule.R
import com.example.demomodule.base.BasesActivity
import com.example.demomodule.databinding.ActivityOutletDetailsBinding
import com.example.demomodule.entity.Users
import com.google.android.gms.maps.model.LatLng
import com.rosia.domain.outletDetail.CallsHistory
import com.rosia.domain.outletDetail.OutletsDetail
import com.rosia.googlemap.MapsFragment
import com.rosia.orderhistory.OrdersHistoryActivity
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_outlet_details.*
import kotlinx.android.synthetic.main.content_outlet_details.view.*
import javax.inject.Inject


class OutletsDetailsActivity : BasesActivity(),OutletDetailsPageContract.View {

    @Inject
    lateinit var outletDetailsPresenter: OutletDetailsPageContract.Presenters

    private lateinit var outletBinding: ActivityOutletDetailsBinding
    private val groupAdapter = GroupAdapter<ViewHolder>()
    private var outletId=0
    private var fabVisibility=false
    lateinit var token:String
    private var outletLocation: LatLng = LatLng(0.0, 0.0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        outletBinding = DataBindingUtil.setContentView(this, R.layout.activity_outlet_details)
        setSupportActionBar(toolbar)
        outletBinding.toolbar.setNavigationOnClickListener({ onBackPressed() })

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowTitleEnabled(false)
        }

        outletId = intent.getIntExtra("id", 0)
        fabVisibility = intent.getBooleanExtra("visibility",false)
        token = intent.getStringExtra("token")

        outletDetailsPresenter.saveUserToken(Users(token))
        outletDetailsPresenter.onGetOutletData(outletId)
        outletBinding.root.rv_outlet_detail.apply {
            layoutManager = LinearLayoutManager(this@OutletsDetailsActivity)
            adapter = groupAdapter
        }

        outletBinding.root.tv_order_history.setOnClickListener {
            OrdersHistoryActivity.start(this, outletId)
        }

        if (fabVisibility){
            outletBinding.favStartCall.visibility=View.VISIBLE
            outletBinding.favStartCall.setOnClickListener (FabButtonClickListener())

        }

    }


    override fun getCallHistorySuccess(callsHistoryList: List<CallsHistory>) {
        val list: List<CallsHistoryChildItem> = callsHistoryList.map { CallsHistoryChildItem(it) }
        if (!list.isEmpty())
            ExpandableGroup(CallsHistoryParentItem(""), false).apply {
                add(Section(list))
                groupAdapter.add(this)
            }
    }


    override fun getOutletDetailSuccess(outletsDetail: OutletsDetail) {
        outletBinding.outlet = outletsDetail
        outletId = this.outletId
        outletBinding.root.tv_order_history.setOnClickListener {
            OrdersHistoryActivity.start(this, outletId)
        }
        outletLocation = LatLng(outletsDetail.latitude!!.toDouble(), outletsDetail.longitude!!.toDouble())
        supportFragmentManager
                .beginTransaction()
                .add(outletBinding.root.fragment.id, MapsFragment.newInstance(outletLocation)).commit()
    }


    override fun showError(errorMessage: String) {
        super.showError(outletBinding, errorMessage)
    }

    override fun showLoading(loading: String) {
        super.showLoading(outletBinding)
    }

    companion object {
        fun start(context: Context?,token:String, outletId: Int, fabButtonVisibility:Boolean=false) =context?.startActivity(Intent(context, OutletsDetailsActivity::class.java)
                .putExtra("id", outletId)
                .putExtra("visibility",fabButtonVisibility)
                .putExtra("token",token))


    }

}
