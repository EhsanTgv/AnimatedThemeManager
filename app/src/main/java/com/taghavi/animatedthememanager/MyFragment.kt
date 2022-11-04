package com.taghavi.animatedthememanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dolatkia.animatedThemeManager.AppTheme
import com.dolatkia.animatedThemeManager.ThemeFragment
import com.dolatkia.animatedThemeManager.ThemeManager
import com.taghavi.animatedthememanager.databinding.FragmentBinding
import com.taghavi.animatedthememanager.themes.LightTheme
import com.taghavi.animatedthememanager.themes.MyAppTheme
import com.taghavi.animatedthememanager.themes.NightTheme
import com.taghavi.animatedthememanager.themes.PinkTheme

class MyFragment: ThemeFragment(){
    private lateinit var binding: FragmentBinding
    private var number: Int = 0

    companion object {
        @JvmStatic
        fun newInstance(number: Int) = MyFragment().apply {
            arguments = Bundle().apply {
                putInt("NUMBER", number)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt("NUMBER")?.let {
            number = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // create and bind views
        binding = FragmentBinding.inflate(inflater)

        // set fragmentNumber
        binding.fragmentNumber.text = number.toString()

        //set next fragment click listener
        binding.nextFragmentButton.setOnClickListener {
            (activity as MyFragmentActivity).addNewFragment()
        }

        // set change theme click listeners for buttons
        binding.lightButton.setOnClickListener {
            ThemeManager.instance.changeTheme(LightTheme(), it)
        }
        binding.nightButton.setOnClickListener {
            ThemeManager.instance.changeTheme(NightTheme(), it)
        }
        binding.pinkButton.setOnClickListener {
            ThemeManager.instance.changeTheme(PinkTheme(), it)
        }


        return binding.root
    }

    // to sync ui with selected theme
    override fun syncTheme(appTheme: AppTheme) {
        // change ui colors with new appThem here

        val myAppTheme = appTheme as MyAppTheme
        context?.let {
            // set background color
            binding.root.setBackgroundColor(myAppTheme.activityBackgroundColor(it))

            //set top image
            binding.image.setImageResource(myAppTheme.activityImageRes(it))

            // set icons color
            binding.share.setColorFilter(myAppTheme.activityIconColor(it))
            binding.gift.setColorFilter(myAppTheme.activityIconColor(it))

            //set text color
            binding.text.setTextColor(myAppTheme.activityTextColor(it))
            binding.fragmentNumber.setTextColor(myAppTheme.activityTextColor(it))

            //set card view colors
            binding.lightButton.setCardBackgroundColor(appTheme.activityThemeButtonColor(it))
            binding.nightButton.setCardBackgroundColor(appTheme.activityThemeButtonColor(it))
            binding.pinkButton.setCardBackgroundColor(appTheme.activityThemeButtonColor(it))
            binding.nextFragmentButton.setCardBackgroundColor(appTheme.activityThemeButtonColor(it))
        }


        //syncStatusBarIconColors
        syncStatusBarIconColors(appTheme)
    }

    private fun syncStatusBarIconColors(theme: MyAppTheme) {
        activity?.let {
            ThemeManager.instance.syncStatusBarIconsColorWithBackground(
                it,
                theme.activityBackgroundColor(it)
            )
        }
        activity?.let {
            ThemeManager.instance.syncNavigationBarButtonsColorWithBackground(
                it,
                theme.activityBackgroundColor(it)
            )
        }
    }
}