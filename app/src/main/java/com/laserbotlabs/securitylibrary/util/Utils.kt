package com.laserbotlabs.securitylibrary.util

import com.laserbotlabs.securitylibrary.R
import kotlin.random.Random

class Utils {
    companion object {
        const val EXTRA_INT = "com.laserbotlabs.securitylibrary.INT"
        const val EXTRA_STRING = "com.laserbotlabs.securitylibrary.STRING"

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
            val randomNumber = Random.nextInt(colors.size)
            return colors[randomNumber]
        }
    }
}