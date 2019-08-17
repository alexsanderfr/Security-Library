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
import com.laserbotlabs.securitylibrary.adapter.ThreatsAdapter
import com.laserbotlabs.securitylibrary.model.Threat
import com.laserbotlabs.securitylibrary.viewmodel.ThreatViewModel
import kotlinx.android.synthetic.main.fragment_threats.*

class ThreatsFragment : Fragment(){

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        threats_rv.layoutManager = GridLayoutManager(activity, 2)
        threats_rv.setHasFixedSize(true)
        val model = ViewModelProviders.of(this).get(ThreatViewModel::class.java)
        model.getThreats().observe(this, Observer<List<Threat>>{ threats ->
            threats_rv.adapter = ThreatsAdapter(this.activity!!, threats!!)
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
