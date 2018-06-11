package com.example.rumi.outletutildemo

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.rumi.outletutildemo.databinding.ActivityMainBinding
import com.rosia.outletdetail.OutletDetailActivity

class MainActivity : AppCompatActivity() {
    private lateinit var mainActivity: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        mainActivity = DataBindingUtil.setContentView(this, R.layout.activity_main)


        startActivity(OutletDetailActivity.getIntent(this,1))
    }
}
