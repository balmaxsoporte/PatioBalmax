package com.balmax.patiobalmax

object UserData {
    val users = listOf(User("admin", "admin", true, true, true))

    data class User(
        val username: String,
        val password: String,
        val editUsers: Boolean,
        val editParkingLots: Boolean,
        val editLicensePlates: Boolean
    )
}
