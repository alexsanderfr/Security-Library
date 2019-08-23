package com.laserbotlabs.securitylibrary.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.laserbotlabs.securitylibrary.R
import com.laserbotlabs.securitylibrary.model.Resource
import com.laserbotlabs.securitylibrary.model.Threat
import com.laserbotlabs.securitylibrary.model.Vulnerability
import com.laserbotlabs.securitylibrary.util.Utils.Companion.EXTRA_INT
import com.laserbotlabs.securitylibrary.util.Utils.Companion.EXTRA_STRING
import com.laserbotlabs.securitylibrary.util.ViewModelFactory
import com.laserbotlabs.securitylibrary.viewmodel.ResourceViewModel
import com.laserbotlabs.securitylibrary.viewmodel.ThreatViewModel
import com.laserbotlabs.securitylibrary.viewmodel.VulnerabilityViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        when (arguments?.getString(EXTRA_STRING, "threat")) {
            "threat" -> {
                loadThreats()
            }
            "vulnerability" -> {
                loadVulnerabilities()
            }
            "resource" -> {
                loadResources()
            }
        }
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    private fun loadThreats() {
        val text = resources.openRawResource(R.raw.data).bufferedReader().use { it.readText() }
        val model = ViewModelProviders.of(this, ViewModelFactory(arrayOf(text)))
            .get(ThreatViewModel::class.java)
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
    }

    private fun loadVulnerabilities() {
        val text = resources.openRawResource(R.raw.data).bufferedReader().use { it.readText() }
        val model = ViewModelProviders.of(this, ViewModelFactory(arrayOf(text)))
            .get(VulnerabilityViewModel::class.java)
        model.getVulnerabilities().observe(this, Observer<List<Vulnerability>> { vulnerabilities ->
            val position = arguments?.getInt(EXTRA_INT, 0)
            val vulnerability = position?.let { vulnerabilities?.get(it) }
            if (vulnerability != null) {
                photo_iv.setImageResource(R.drawable.ic_bug)
                photo_iv.setColorFilter(
                    ContextCompat.getColor(this.activity!!, vulnerability.imageResource),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
                title_tv.text = vulnerability.name
                body_tv.text = vulnerability.description
            }
        })
    }

    private fun loadResources() {
        val text = resources.openRawResource(R.raw.data).bufferedReader().use { it.readText() }
        val model = ViewModelProviders.of(this, ViewModelFactory(arrayOf(text))).get(
            ResourceViewModel::class.java
        )
        model.getResources().observe(this, Observer<List<Resource>> { resources ->
            val position = arguments?.getInt(EXTRA_INT, 0)
            val resource = position?.let { resources?.get(it) }
            if (resource != null) {
                photo_iv.setImageResource(R.drawable.ic_bug)
                photo_iv.setColorFilter(
                    ContextCompat.getColor(this.activity!!, resource.imageResource),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
                title_tv.text = resource.name
                body_tv.text = resource.description
            }
        })
    }
}