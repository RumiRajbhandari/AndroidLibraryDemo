package com.example.demomodule

import android.view.View

/**
 * Created by rumi on 6/12/18.
 */
open class FabButtonClickListener : View.OnClickListener{
    companion object {
        protected var onFabButtonClicked:OnFabButtonClicked?=null

        fun setOnFabButtonClickListener(onFabButtonClicked: OnFabButtonClicked){
            this.onFabButtonClicked= onFabButtonClicked
        }
    }

    override fun onClick(p0: View?) {
        onFabButtonClicked?.onClickButton()
    }
}