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
import com.laserbotlabs.securitylibrary.util.Utils
import com.laserbotlabs.securitylibrary.viewmodel.ThreatViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import org.jetbrains.anko.support.v4.toast

class ListFragment : Fragment(){

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        items_rv.layoutManager = GridLayoutManager(activity, 2)
        items_rv.setHasFixedSize(true)
        val position = arguments?.getInt(Utils.EXTRA_INT, 0)
        when(position) {
            0 -> {
                toast("Position #$position")
                loadThreats()
            }
            1 -> {
                toast("Position #$position")
                loadThreats()
            }
            2 -> {
                toast("Position #$position")
                loadThreats()
            }
        }
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    fun loadThreats() {
        val model = ViewModelProviders.of(this).get(ThreatViewModel::class.java)
        model.getThreats().observe(this, Observer<List<Threat>>{ threats ->
            items_rv.adapter = ThreatsAdapter(this.activity!!, threats!!)
        })
    }


}
