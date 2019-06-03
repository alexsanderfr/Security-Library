package com.laserbotlabs.securitylibrary.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.laserbotlabs.securitylibrary.R
import com.laserbotlabs.securitylibrary.fragment.ThreatDetailFragment


const val EXTRA_INT = "com.laserbotlabs.securitylibrary.INT"

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val position = intent.getIntExtra(com.laserbotlabs.securitylibrary.fragment.EXTRA_INT, 0)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.content_frame, createFragment(position))
        transaction.commit()
    }

    private fun createFragment(position:Int): ThreatDetailFragment {
        val fragment = ThreatDetailFragment()
        val bundle = Bundle()
        bundle.putInt(EXTRA_INT, position)
        fragment.arguments = bundle
        return fragment
    }
}
