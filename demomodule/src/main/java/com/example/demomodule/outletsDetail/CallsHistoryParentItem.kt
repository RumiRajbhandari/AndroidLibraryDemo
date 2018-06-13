package com.rosia.outletdetail

import android.annotation.SuppressLint
import android.support.v4.content.ContextCompat
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import com.example.demomodule.R
import com.example.demomodule.outletsDetail.getTotalNoOfDays
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_call_history_header.view.*


class CallsHistoryParentItem (private val title:String) : Item(), ExpandableItem{
    private lateinit var expandableGroup:ExpandableGroup
    lateinit var imageView: ImageView
    @SuppressLint("SetTextI18n")
    override fun bind(viewHolder: ViewHolder, position: Int) {
        val context = viewHolder.itemView.context
        viewHolder.itemView.tv_last_call.text = "Last called ${getTotalNoOfDays(title)} days ago"
        viewHolder.itemView.setOnClickListener {
            expandableGroup.onToggleExpanded()
            imageView = viewHolder.itemView.iv_arrow
            setAnimation()
            imageView.setImageResource(getRotatedIconResId())
            viewHolder.itemView.item_call_history.setTextColor(ContextCompat.getColor(context,getColorRes())  )
        }
    }

    private fun setAnimation() {
        if(expandableGroup.isExpanded)
            animateExpand()
        else
            animateCollapse()

    }

    override fun getLayout(): Int {
        return R.layout.item_call_history_header
    }

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        expandableGroup = onToggleListener
    }

    private fun getRotatedIconResId() =
            if (expandableGroup.isExpanded) {
                R.drawable.ic_arrow_down_blue_vector
            }
            else {
                R.drawable.ic_arrow_down_vector
            }
    private fun getColorRes() =
            if (expandableGroup.isExpanded)
                R.color.colorPrimary
            else
                R.color.primary_text_color


    private fun animateExpand(){
        val rotate = RotateAnimation(360f,180f, Animation.RELATIVE_TO_SELF,  0.5f, Animation.RELATIVE_TO_SELF,0.5f)
        rotate.duration = 300
        rotate.fillAfter = true
        rotate.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
               imageView.setImageResource(getRotatedIconResId())
            }
        })
        imageView.startAnimation(rotate)
    }

    private fun animateCollapse(){
        val rotate = RotateAnimation(180f,360f, Animation.RELATIVE_TO_SELF,  0.5f, Animation.RELATIVE_TO_SELF,0.5f)
        rotate.duration = 300
        rotate.fillAfter = true
        rotate.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                imageView.setImageResource(getRotatedIconResId())
            }
        })
        imageView.startAnimation(rotate)
    }
}