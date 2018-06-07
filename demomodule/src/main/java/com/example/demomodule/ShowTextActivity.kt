package com.example.demomodule

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_show_text.*

class ShowTextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_text)
        var text = intent.getStringExtra("text" )
        textView.text=text

    }
    companion object {
        fun getIntent(context: Context?, text: String): Intent = Intent(context, ShowTextActivity::class.java).putExtra("text", text)
    }
}
