package com.taghavi.animatedthememanager

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.taghavi.animatedthememanager.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        val binder = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binder.root)

        binder.singleActivitySampleButton.setOnClickListener {
            val myIntent = Intent(this, SingleActivity::class.java)
            this.startActivity(myIntent)
        }

        // set click listener for fragmentSampleButton
        binder.fragmentSampleButton.setOnClickListener {
            val myIntent = Intent(this, MyFragmentActivity::class.java)
            this.startActivity(myIntent)
        }

        // set click listener for reverseAnimation
        binder.reverseAnimation.setOnClickListener {
            val myIntent = Intent(this, ReverseActivity::class.java)
            this.startActivity(myIntent)
        }
    }
}