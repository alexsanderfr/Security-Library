package com.laserbotlabs.securitylibrary.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.laserbotlabs.securitylibrary.R
import com.laserbotlabs.securitylibrary.util.Utils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        findViewById<Toolbar>(R.id.toolbar).setupWithNavController(navController, appBarConfiguration)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
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
                R.id.securityResourceMenuItem -> {
                    val bundle = bundleOf(Utils.EXTRA_INT to 2)
                    navController.navigate(R.id.listFragment, bundle)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val bundle = bundleOf(Utils.EXTRA_INT to 0)
        navController.navigate(R.id.listFragment, bundle)
    }
}
