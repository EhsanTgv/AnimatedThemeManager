package com.taghavi.animatedthememanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import com.dolatkia.animatedThemeManager.AppTheme
import com.dolatkia.animatedThemeManager.ThemeActivity
import com.dolatkia.animatedThemeManager.ThemeManager
import com.taghavi.animatedthememanager.databinding.ActivitySingleBinding
import com.taghavi.animatedthememanager.themes.LightTheme
import com.taghavi.animatedthememanager.themes.MyAppTheme
import com.taghavi.animatedthememanager.themes.NightTheme
import com.taghavi.animatedthememanager.themes.PinkTheme

class SingleActivity : ThemeActivity() {
    private lateinit var binding: ActivitySingleBinding
    private var number: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        number = intent.getIntExtra("number", 1)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        )

        binding = ActivitySingleBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.ActivityNumber.text = number.toString()

        binding.lightButton.setOnClickListener {
            ThemeManager.instance.changeTheme(LightTheme(), it)
        }
        binding.nightButton.setOnClickListener {
            ThemeManager.instance.changeTheme(NightTheme(), it)
        }
        binding.pinkButton.setOnClickListener {
            ThemeManager.instance.changeTheme(PinkTheme(), it)
        }
        binding.nextActivityBtn.setOnClickListener {
            val myIntent = Intent(this, SingleActivity::class.java)
            myIntent.putExtra("number", number + 1)
            this.startActivity(myIntent)
        }
    }

    override fun syncTheme(appTheme: AppTheme) {
        val myAppTheme = appTheme as MyAppTheme

        binding.root.setBackgroundColor(myAppTheme.activityBackgroundColor(this))

        binding.image.setImageResource(myAppTheme.activityImageRes(this))

        binding.share.setColorFilter(myAppTheme.activityIconColor(this))
        binding.gift.setColorFilter(myAppTheme.activityIconColor(this))

        binding.text.setTextColor(myAppTheme.activityTextColor(this))

        binding.lightButton.setCardBackgroundColor(appTheme.activityThemeButtonColor(this))
        binding.nightButton.setCardBackgroundColor(appTheme.activityThemeButtonColor(this))
        binding.pinkButton.setCardBackgroundColor(appTheme.activityThemeButtonColor(this))
        binding.nextActivityBtn.setCardBackgroundColor(appTheme.activityThemeButtonColor(this))

        syncStatusBarIconColors(appTheme)
    }

    // to get stat theme
    override fun getStartTheme(): AppTheme {
        return LightTheme()
    }

    private fun syncStatusBarIconColors(theme: MyAppTheme) {
        ThemeManager.instance.syncStatusBarIconsColorWithBackground(
            this,
            theme.activityBackgroundColor(this)
        )
        ThemeManager.instance.syncNavigationBarButtonsColorWithBackground(
            this,
            theme.activityBackgroundColor(this)
        )
    }
}