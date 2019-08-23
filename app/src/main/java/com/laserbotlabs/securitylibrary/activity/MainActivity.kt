package com.laserbotlabs.securitylibrary.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.laserbotlabs.securitylibrary.R
import com.laserbotlabs.securitylibrary.util.Utils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(
            navController,
            appBarConfiguration
        )

        val onNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.threatMenuItem -> {
                        val bundle = bundleOf(Utils.EXTRA_INT to 0)
                        navController.navigate(R.id.listFragment, bundle)
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.vulnerabilityMenuItem -> {
                        val bundle = bundleOf(Utils.EXTRA_INT to 1)
                        navController.navigate(R.id.listFragment, bundle)
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.resourceMenuItem -> {
                        val bundle = bundleOf(Utils.EXTRA_INT to 2)
                        navController.navigate(R.id.listFragment, bundle)
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            }
        bottom_nav.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val navOptions = NavOptions.Builder().setPopUpTo(R.id.listFragment, true).build()
        navController.navigate(R.id.listFragment, bundleOf(Utils.EXTRA_INT to 0), navOptions)

    }

    fun updateBottomNavigationViewUi(position: Int) {
        var itemId = R.id.threatMenuItem
        when (position) {
            0 -> {
                itemId = R.id.threatMenuItem
            }
            1 -> {
                itemId = R.id.vulnerabilityMenuItem
            }
            2 -> {
                itemId = R.id.resourceMenuItem
            }
        }
        val menu = bottom_nav.menu
        val item = menu.findItem(itemId)
        item.isChecked = true
    }
}
