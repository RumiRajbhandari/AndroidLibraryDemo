package com.example.demomodule.base

import android.content.Context
import android.databinding.ViewDataBinding
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.demomodule.R
import dagger.android.support.DaggerAppCompatActivity


abstract class BaseActivity : DaggerAppCompatActivity() {

    fun <T : ViewDataBinding> showLoading(binding: T?) {
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)?.visibility = View.VISIBLE
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)?.findViewById<TextView>(R.id.txtLoading)?.text = getString(R.string.loading)
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)?.findViewById<ProgressBar>(R.id.progressBar)?.visibility = View.VISIBLE
    }

    fun <T : ViewDataBinding> showData(binding: T?) {
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)?.visibility = View.GONE
    }

    fun <T : ViewDataBinding> showError(binding: T?, errorMessage: String) {
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)?.visibility = View.VISIBLE
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)?.findViewById<TextView>(R.id.txtLoading)?.text = getString(R.string.error)
        binding?.root?.findViewById<RelativeLayout>(R.id.loadingLayout)?.findViewById<ProgressBar>(R.id.progressBar)?.visibility = View.INVISIBLE
    }

    fun getContext():Context{
        return this
    }

}