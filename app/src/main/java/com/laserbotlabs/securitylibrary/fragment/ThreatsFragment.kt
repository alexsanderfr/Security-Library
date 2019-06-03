package com.laserbotlabs.securitylibrary.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.laserbotlabs.securitylibrary.R
import com.laserbotlabs.securitylibrary.activity.DetailActivity
import com.laserbotlabs.securitylibrary.adapter.ThreatsAdapter
import com.laserbotlabs.securitylibrary.model.Threat
import com.laserbotlabs.securitylibrary.viewmodel.ThreatViewModel
import kotlinx.android.synthetic.main.fragment_threats.*

const val EXTRA_INT = "com.laserbotlabs.securitylibrary.INT"

class ThreatsFragment : Fragment(), ThreatsAdapter.ThreatsAdapterOnClickHandler {

    override fun onClick(position: Int) {
        val intent = Intent(activity, DetailActivity::class.java).apply {
            putExtra(EXTRA_INT, position)
        }
        startActivity(intent)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        threats_rv.layoutManager = GridLayoutManager(activity, 2)
        threats_rv.setHasFixedSize(true)
        val model = ViewModelProviders.of(this).get(ThreatViewModel::class.java)
        model.getThreats().observe(this, Observer<List<Threat>>{ threats ->
            threats_rv.adapter = ThreatsAdapter(this.activity!!, threats!!, this)
        })
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_threats, container, false)
    }


}
