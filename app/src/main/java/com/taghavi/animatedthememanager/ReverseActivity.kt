package com.taghavi.animatedthememanager

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import com.dolatkia.animatedThemeManager.AppTheme
import com.dolatkia.animatedThemeManager.ThemeActivity
import com.dolatkia.animatedThemeManager.ThemeManager
import com.taghavi.animatedthememanager.databinding.ActivityReverseBinding
import com.taghavi.animatedthememanager.themes.LightTheme
import com.taghavi.animatedthememanager.themes.MyAppTheme
import com.taghavi.animatedthememanager.themes.NightTheme

class ReverseActivity : ThemeActivity() {

    private lateinit var binding: ActivityReverseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        binding = ActivityReverseBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        updateButtonText()
        binding.button.setOnClickListener {
            if (ThemeManager.instance.getCurrentTheme()
                    ?.id() == NightTheme.ThemeId
            ) {
                ThemeManager.instance.reverseChangeTheme(LightTheme(), it)
            } else if (ThemeManager.instance.getCurrentTheme()
                    ?.id() != NightTheme.ThemeId
            ) {
                ThemeManager.instance.changeTheme(NightTheme(), it)
            }
            updateButtonText()
        }
    }

    override fun syncTheme(appTheme: AppTheme) {
        val myAppTheme = appTheme as MyAppTheme

        binding.root.setBackgroundColor(myAppTheme.activityBackgroundColor(this))

        binding.image.setImageResource(myAppTheme.activityImageRes(this))

        binding.share.setColorFilter(myAppTheme.activityIconColor(this))
        binding.gift.setColorFilter(myAppTheme.activityIconColor(this))

        binding.text.setTextColor(myAppTheme.activityTextColor(this))

        binding.button.setCardBackgroundColor(appTheme.activityThemeButtonColor(this))

        syncStatusBarIconColors(appTheme)
    }

    fun updateButtonText() {
        if (ThemeManager.instance.getCurrentTheme()?.id() == NightTheme.ThemeId) {
            binding.buttonTextView.text = "Light"
        } else {
            binding.buttonTextView.text = "Night"
        }
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