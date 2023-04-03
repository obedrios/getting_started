

import minipractices.taxipark.*
import java.io.File


fun main(args: Array<String>) {
    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    // println("Program arguments: ${args.joinToString()}"
    //println(generateTaxiParkDataSet().toJSON())

    val taxiParkJson: File = File(ClassLoader.getSystemResource("data/taxipark.json").path)
    val taxiPark = readTaxiParkFromJSON(taxiParkJson.readText())
    //
    //taxiPark.findDriversWithNoTrips().forEach { println(it) }
    //taxiPark.findTripsWithNoPassengers().forEach { println(it) }
    //taxiPark.findFaithfulPassengers2(5).forEach { println(it) }
    //taxiPark.findFrequentPassengers(taxiPark.allDrivers.toTypedArray()[5]).forEach { println(it) }

}

