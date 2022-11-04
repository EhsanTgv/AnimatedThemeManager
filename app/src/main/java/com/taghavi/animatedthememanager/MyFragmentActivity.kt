package com.taghavi.animatedthememanager

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import com.dolatkia.animatedThemeManager.AppTheme
import com.dolatkia.animatedThemeManager.ThemeActivity
import com.taghavi.animatedthememanager.databinding.ActivityMyFragmentBinding
import com.taghavi.animatedthememanager.themes.LightTheme

class MyFragmentActivity : ThemeActivity() {
    private var fragmentNumber: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // full screen app
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        // create and bind views
        val binding = ActivityMyFragmentBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        // add first fragment
        addNewFragment()
    }


    fun addNewFragment() {
        fragmentNumber++
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragmentContainer, MyFragment.newInstance(fragmentNumber))
        if (fragmentNumber != 1) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    override fun syncTheme(appTheme: AppTheme) {
        // there is nothing in activity to sync
        // inner fragments do this
    }

    // to get stat theme
    override fun getStartTheme(): AppTheme {
        return LightTheme()
    }
}