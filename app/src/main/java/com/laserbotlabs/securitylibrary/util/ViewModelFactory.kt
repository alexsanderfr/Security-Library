package com.laserbotlabs.securitylibrary.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.laserbotlabs.securitylibrary.viewmodel.ResourceViewModel
import com.laserbotlabs.securitylibrary.viewmodel.AttackViewModel
import com.laserbotlabs.securitylibrary.viewmodel.VulnerabilityViewModel

class ViewModelFactory(private val params: Array<Any>) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == AttackViewModel::class.java) {
            return AttackViewModel(params[0] as String) as T
        } else if (modelClass == VulnerabilityViewModel::class.java) {
            return VulnerabilityViewModel(params[0] as String) as T
        } else if (modelClass == ResourceViewModel::class.java) {
            return ResourceViewModel(params[0] as String) as T
        } else {
            return super.create(modelClass)
        }
    }
}