package com.laserbotlabs.securitylibrary.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.laserbotlabs.securitylibrary.R
import com.laserbotlabs.securitylibrary.activity.MainActivity
import com.laserbotlabs.securitylibrary.adapter.ResourcesAdapter
import com.laserbotlabs.securitylibrary.adapter.ThreatsAdapter
import com.laserbotlabs.securitylibrary.adapter.VulnerabilitiesAdapter
import com.laserbotlabs.securitylibrary.model.Resource
import com.laserbotlabs.securitylibrary.model.Threat
import com.laserbotlabs.securitylibrary.model.Vulnerability
import com.laserbotlabs.securitylibrary.util.Utils
import com.laserbotlabs.securitylibrary.util.ViewModelFactory
import com.laserbotlabs.securitylibrary.viewmodel.ResourceViewModel
import com.laserbotlabs.securitylibrary.viewmodel.ThreatViewModel
import com.laserbotlabs.securitylibrary.viewmodel.VulnerabilityViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment(){

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        items_rv.layoutManager = GridLayoutManager(activity, 2)
        items_rv.setHasFixedSize(true)
        when(arguments?.getInt(Utils.EXTRA_INT, 0)) {
            0 -> {
                loadThreats()
            }
            1 -> {
                loadVulnerabilities()
            }
            2 -> {
                loadResources()
            }
        }

        super.onActivityCreated(savedInstanceState)
    }

    override fun onResume() {
        val position = arguments?.getInt(Utils.EXTRA_INT, 0)
        if (position != null) {
            (activity as MainActivity).updateBottomNavigationViewUi(position)
        }
        super.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    private fun loadThreats() {
        val text = resources.openRawResource(R.raw.data).bufferedReader().use { it.readText() }
        val model = ViewModelProviders.of(this, ViewModelFactory(arrayOf(text))).get(ThreatViewModel::class.java)
        model.getThreats().observe(this, Observer<List<Threat>>{ threats ->
            items_rv.adapter = ThreatsAdapter(this.activity!!, threats!!)
        })
    }

    private fun loadVulnerabilities() {
        val text = resources.openRawResource(R.raw.data).bufferedReader().use { it.readText() }
        val model = ViewModelProviders.of(this, ViewModelFactory(arrayOf(text))).get(VulnerabilityViewModel::class.java)
        model.getVulnerabilities().observe(this, Observer<List<Vulnerability>>{ vulnerabilities ->
            items_rv.adapter = VulnerabilitiesAdapter(this.activity!!, vulnerabilities!!)
        })
    }

    private fun loadResources() {
        val text = resources.openRawResource(R.raw.data).bufferedReader().use { it.readText() }
        val model = ViewModelProviders.of(this, ViewModelFactory(arrayOf(text))).get(
            ResourceViewModel::class.java)
        model.getResources().observe(this, Observer<List<Resource>>{ resources ->
            items_rv.adapter = ResourcesAdapter(this.activity!!, resources!!)
        })
    }


}
