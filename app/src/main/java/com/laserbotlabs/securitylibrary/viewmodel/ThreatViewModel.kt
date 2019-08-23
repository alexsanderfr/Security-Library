package com.laserbotlabs.securitylibrary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.laserbotlabs.securitylibrary.model.Threat
import com.laserbotlabs.securitylibrary.util.Utils
import org.json.JSONObject


class ThreatViewModel(private val json:String): ViewModel() {
    private val threats: MutableLiveData<List<Threat>> = MutableLiveData()

    init {
        loadThreats()
    }

    fun getThreats(): LiveData<List<Threat>> {
        return threats
    }

    private fun loadThreats() {
        val jsonObject = JSONObject(json)
        val threatsJsonArray = jsonObject.getJSONArray("threats")
        val threatsArrayList: ArrayList<Threat> = ArrayList()
        for (i in 0 until threatsJsonArray.length()) {
            val threatJsonObject = threatsJsonArray.getJSONObject(i)
            val name = threatJsonObject.getString("name")
            val description = threatJsonObject.getString("description")
            val canBeTested = threatJsonObject.getBoolean("canBeTested")
            val imageResource = threatJsonObject.getInt("imageResource")
            val threat = Threat(name, description, canBeTested,
                if (imageResource == -1) Utils.getRandomColor() else imageResource)
            threatsArrayList.add(threat)
        }
        threats.value = threatsArrayList
    }
}