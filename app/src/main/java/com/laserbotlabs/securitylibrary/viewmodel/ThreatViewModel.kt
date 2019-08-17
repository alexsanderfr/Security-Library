package com.laserbotlabs.securitylibrary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.laserbotlabs.securitylibrary.model.Threat
import com.laserbotlabs.securitylibrary.util.Utils


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
            val threat = Threat(name, description, x % 3 == 0, Utils.getRandomColor())
            threatsArrayList.add(threat)
        }
        threats.value = threatsArrayList
    }
}