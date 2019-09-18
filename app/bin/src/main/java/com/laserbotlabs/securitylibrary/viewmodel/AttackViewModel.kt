package com.laserbotlabs.securitylibrary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.laserbotlabs.securitylibrary.model.Attack
import com.laserbotlabs.securitylibrary.util.Utils
import org.json.JSONObject


class AttackViewModel(private val json:String): ViewModel() {
    private val attacks: MutableLiveData<List<Attack>> = MutableLiveData()

    init {
        loadAttacks()
    }

    fun getAttacks(): LiveData<List<Attack>> {
        return attacks
    }

    private fun loadAttacks() {
        val jsonObject = JSONObject(json)
        val attacksJsonArray = jsonObject.getJSONArray("attacks")
        val attacksArrayList: ArrayList<Attack> = ArrayList()
        for (i in 0 until attacksJsonArray.length()) {
            val attackJsonObject = attacksJsonArray.getJSONObject(i)
            val name = attackJsonObject.getString("name")
            val description = attackJsonObject.getString("description")
            val canBeTested = attackJsonObject.getBoolean("canBeTested")
            val imageResource = attackJsonObject.getInt("imageResource")
            val attack = Attack(name, description, canBeTested, Utils.getImageResource(imageResource))
            attacksArrayList.add(attack)
        }
        attacks.value = attacksArrayList
    }
}