package com.laserbotlabs.securitylibrary.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.laserbotlabs.securitylibrary.R
import com.laserbotlabs.securitylibrary.model.Threat
import com.laserbotlabs.securitylibrary.viewmodel.ThreatViewModel
import kotlinx.android.synthetic.main.fragment_threat_detail.*

class ThreatDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val model = ViewModelProviders.of(this).get(ThreatViewModel::class.java)
        model.getThreats().observe(this, Observer<List<Threat>> { threats ->
            val position = arguments?.getInt(EXTRA_INT, 0)
            val threat = position?.let { threats?.get(it) }
            if (threat != null) {
                photo_iv.setImageResource(R.drawable.ic_bug)
                photo_iv.setColorFilter(
                    ContextCompat.getColor(this.activity!!, threat.imageResource),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
                title_tv.text = threat.name
                body_tv.text = threat.description
                if (threat.canBeTested) play_fab.show() else play_fab.hide()
            }
        })
        return inflater.inflate(R.layout.fragment_threat_detail, container, false)
    }
}
