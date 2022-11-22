package com.example.batcomputer

import kotlin.random.Random

data class CriminalModel(
    var case: Int = getCaseNo(),
    var name: String,
    var age: Int,
    var crime:String,
    var imprisonment: Int,
    var isSolved: String
) {
    companion object {
        fun getCaseNo(): Int {
            val random = Random
            return random.nextInt(999)
        }
    }
}
