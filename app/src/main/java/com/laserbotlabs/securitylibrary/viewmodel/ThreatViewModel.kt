package com.laserbotlabs.securitylibrary.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.laserbotlabs.securitylibrary.model.Threat


class ThreatViewModel : ViewModel() {
    private val threats: MutableLiveData<List<Threat>> = MutableLiveData()

    init {
        loadThreats()
    }

    fun getThreats(): LiveData<List<Threat>> {
        return threats
    }

    private fun loadThreats() {
        val threatsArrayList: ArrayList<Threat> = ArrayList()
        for (x in 0..10) {
            val name = "Threat #$x"
            val description = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod " +
                    "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud " +
                    "exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
            val threat = Threat(name, description, x % 3 == 0)
            threatsArrayList.add(threat)
        }
        threats.value = threatsArrayList
    }
}