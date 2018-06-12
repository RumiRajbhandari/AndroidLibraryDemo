package com.example.demomodule

import android.view.View

/**
 * Created by rumi on 6/12/18.
 */
open class FabButtonClickListener : View.OnClickListener{
    companion object {
        protected var onButtonClicked:OnButtonClicked?=null

        fun setOnFabButtonClickListener(onButtonClicked: OnButtonClicked){
            this.onButtonClicked= onButtonClicked
        }
    }

    override fun onClick(p0: View?) {
        onButtonClicked?.onClickButton("hey how are you")
    }
}