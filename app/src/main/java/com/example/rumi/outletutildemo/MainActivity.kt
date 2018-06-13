package com.example.rumi.outletutildemo

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.demomodule.OnFabButtonClicked
import com.example.demomodule.FabButtonClickListener
import com.example.rumi.outletutildemo.databinding.ActivityMainBinding
import com.rosia.outletdetail.OutletsDetailsActivity

class MainActivity : AppCompatActivity(), OnFabButtonClicked {
    private lateinit var mainActivity: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = DataBindingUtil.setContentView(this, R.layout.activity_main)

        OutletsDetailsActivity.start(this, "xyz",
                1,true)
        FabButtonClickListener.setOnFabButtonClickListener(this)
    }

    override fun onClickButton() {
        Toast.makeText(this,"Fab clicked",Toast.LENGTH_SHORT).show()

    }
}
