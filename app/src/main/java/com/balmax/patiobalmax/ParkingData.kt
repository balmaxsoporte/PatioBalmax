package com.balmax.patiobalmax

object ParkingData {
    // Datos simulados
    fun getParkingSpotsForPatio(patioNumber: Int): List<ParkingSpot> {
        return (1..10).map { ParkingSpot(it, patioNumber) }
    }
}

data class ParkingSpot(val number: Int, val patioNumber: Int)
