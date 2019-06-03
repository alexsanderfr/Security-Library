package com.laserbotlabs.securitylibrary.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.laserbotlabs.securitylibrary.R
import com.laserbotlabs.securitylibrary.fragment.ThreatsFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.content_frame, createFragment())
        transaction.commit()
    }

    fun createFragment(): ThreatsFragment {
        return ThreatsFragment()
    }
}
