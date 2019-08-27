package com.laserbotlabs.securitylibrary.util

import com.laserbotlabs.securitylibrary.R
import kotlin.random.Random

class Utils {
    companion object {
        const val EXTRA_INT = "com.laserbotlabs.securitylibrary.INT"
        const val EXTRA_STRING = "com.laserbotlabs.securitylibrary.STRING"
        private val colors = arrayOf(
            R.color.red, R.color.pink, R.color.blue,
            R.color.green, R.color.orange, R.color.yellow, R.color.purple, R.color.teal
        )

        fun getRandomColor(index: Int): Int {

            if (index != -1) {
                return colors[index]
            }
            val randomNumber = Random.nextInt(colors.size)
            return colors[randomNumber]
        }

        fun getImageResource(imageResource: Int): Int {
            if (imageResource < colors.size) {
                return getRandomColor(imageResource)
            }
            return imageResource
        }
    }
}