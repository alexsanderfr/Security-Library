package com.laserbotlabs.securitylibrary.util

import com.laserbotlabs.securitylibrary.R

class Utils {
    companion object {
        fun getRandomColor(): Int {
            val colors: ArrayList<Int> = ArrayList()
            colors.add(R.color.red)
            colors.add(R.color.pink)
            colors.add(R.color.blue)
            colors.add(R.color.green)
            colors.add(R.color.orange)
            colors.add(R.color.yellow)
            colors.add(R.color.purple)
            colors.add(R.color.teal)
            val randomNumber = (Math.random() * ((0 - colors.size) + 1)) + colors.size
            return colors.get(randomNumber.toInt())
        }
    }
}