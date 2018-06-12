package com.rosia.outletdetail

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.demomodule.FabButtonClickListener
import com.example.demomodule.R
import com.example.demomodule.base.BaseActivity
import com.example.demomodule.databinding.ActivityOutletDetailBinding
import com.google.android.gms.maps.model.LatLng
import com.rosia.domain.outletDetail.CallHistory
import com.rosia.domain.outletDetail.OutletDetail
import com.rosia.googlemap.MapFragment
import com.rosia.orderhistory.OrderHistoryActivity
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_outlet_detail.*
import kotlinx.android.synthetic.main.content_outlet_detail.view.*
import javax.inject.Inject


class OutletDetailActivity : BaseActivity(),OutletDetailPageContract.View {

    @Inject
    lateinit var outletDetailPresenter: OutletDetailPageContract.Presenter

    private lateinit var outletBinding: ActivityOutletDetailBinding
    private val groupAdapter = GroupAdapter<ViewHolder>()
    private var outletId=0
    private var fabVisibility=false
    private var outletLocation: LatLng = LatLng(0.0, 0.0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        outletBinding = DataBindingUtil.setContentView(this, R.layout.activity_outlet_detail)
        setSupportActionBar(toolbar)
        outletBinding.toolbar.setNavigationOnClickListener({ onBackPressed() })

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowTitleEnabled(false)
        }

        outletId = intent.getIntExtra("id", 0)
        fabVisibility = intent.getBooleanExtra("visibility",false)

        outletDetailPresenter.onGetOutletData(outletId)
        outletBinding.root.rv_outlet_detail.apply {
            layoutManager = LinearLayoutManager(this@OutletDetailActivity)
            adapter = groupAdapter
        }

        outletBinding.root.tv_order_history.setOnClickListener {
            OrderHistoryActivity.start(this, outletId)
        }

        if (fabVisibility){
            outletBinding.favStartCall.visibility=View.VISIBLE
            outletBinding.favStartCall.setOnClickListener (FabButtonClickListener())

        }

    }


    override fun getCallHistorySuccess(callHistoryList: List<CallHistory>) {
        println("get call history success")
        val list: List<CallHistoryChildItem> = callHistoryList.map { CallHistoryChildItem(it) }
        if (!list.isEmpty())
            //TODO change this
            ExpandableGroup(CallHistoryParentItem("2.20"), false).apply {
                add(Section(list))
                groupAdapter.add(this)
            }
    }


    override fun getOutletDetailSuccess(outletDetail: OutletDetail) {
        println("get oulet detail success")
        outletBinding.outlet = outletDetail
        outletId = this.outletId
        outletBinding.root.tv_order_history.setOnClickListener {
            OrderHistoryActivity.start(this, outletId)
        }
        outletLocation = LatLng(outletDetail.latitude!!.toDouble(), outletDetail.longitude!!.toDouble())
        supportFragmentManager
                .beginTransaction()
                .add(outletBinding.root.fragment.id, MapFragment.newInstance(outletLocation)).commit()
    }


    override fun showError(errorMessage: String) {
        super.showError(outletBinding, errorMessage)
    }

    override fun showLoading(loading: String) {
        super.showLoading(outletBinding)
    }

    companion object {
        fun start(context: Context?, outletId: Int, fabButtonVisibility:Boolean=false) =context?.startActivity(Intent(context, OutletDetailActivity::class.java).putExtra("id", outletId).putExtra("visibility",fabButtonVisibility))


    }

}