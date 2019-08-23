package com.laserbotlabs.securitylibrary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.laserbotlabs.securitylibrary.model.Resource
import com.laserbotlabs.securitylibrary.util.Utils
import org.json.JSONObject


class ResourceViewModel(private val json:String): ViewModel() {
    private val resources: MutableLiveData<List<Resource>> = MutableLiveData()

    init {
        loadResources()
    }

    fun getResources(): LiveData<List<Resource>> {
        return resources
    }

    private fun loadResources() {
        val jsonObject = JSONObject(json)
        val resourcesJsonArray = jsonObject.getJSONArray("resources")
        val resourcesArrayList: ArrayList<Resource> = ArrayList()
        for (i in 0 until resourcesJsonArray.length()) {
            val resourceJsonObject = resourcesJsonArray.getJSONObject(i)
            val name = resourceJsonObject.getString("name")
            val description = resourceJsonObject.getString("description")
            val imageResource = resourceJsonObject.getInt("imageResource")
            val resource = Resource(name, description,
                if (imageResource == -1) Utils.getRandomColor() else imageResource)
            resourcesArrayList.add(resource)
        }
        resources.value = resourcesArrayList
    }
}