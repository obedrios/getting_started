package minipractices.taxipark

import com.google.gson.Gson
import org.apache.commons.math3.distribution.NormalDistribution
import java.io.File
import kotlin.random.Random



/**
 *  TaxiPark user generation routine
 */
enum class RegisteredUserType {PASSENGER, DRIVER}
fun generateRegisteredUserSet(userType: RegisteredUserType, qty: Int = 10): Set<RegisteredUser> {
    // File Names location
    val lastNamesFile: File = File(ClassLoader.getSystemResource("data/lastnames1000.txt").path)
    val personNamesFile: File = File(ClassLoader.getSystemResource("data/names100.txt").path)
    // Read names files and split them
    val lastNames: List<String> = lastNamesFile.readLines()
    val namesStringList = personNamesFile.readLines() as ArrayList<String>
    namesStringList.removeAt(0)
    val personNames: List<Pair<String, String>> = namesStringList.map {
        val names = it.split("\t")
        Pair(names[0], names[1])
    }
    // Generate random names list
    return Array(qty){
        val firstName = if (Random.nextInt(0,2) == 0) personNames.random().first else personNames.random().second
        val lastName = lastNames.random()
        val age = Random.nextInt(20,55)
        val id =  Random.nextInt(0, 9999)
        when(userType) {
            RegisteredUserType.DRIVER -> Driver(firstName, lastName, age, id)
            RegisteredUserType.PASSENGER -> Passenger(firstName, lastName, age, id)
        }
    }.toSet()
}

/**
 * Generate Trips for a given Day
 */
fun generateTaxiParkDataSet(driverQty: Int = 10, passengerQty: Int = 10,
                            maxTripsQty: Int = 20, maxPassengersPerTrip: Int = 6): TaxiPark {
    val registeredDrivers =
        generateRegisteredUserSet(RegisteredUserType.DRIVER, driverQty) as Set<Driver>
    val registeredPassengers =
        generateRegisteredUserSet(RegisteredUserType.PASSENGER, passengerQty) as Set<Passenger>
    val normalDist = NormalDistribution()
    //
    //
    val trips = List(maxTripsQty){
        val tripPassengers: Set<Passenger> =
            List(Random.nextInt(0,maxPassengersPerTrip)){registeredPassengers.random()}.toSet()
        Trip(
            registeredDrivers.random(),
            tripPassengers,
            Random.nextInt(0,45),
            normalDist.inverseCumulativeProbability(Random.nextDouble())*5.0 + 10.0,
            Random.nextDouble(0.0, 0.15)
        )
    }
    //
    return TaxiPark(registeredDrivers, registeredPassengers, trips)
}

/**
 * Serialize TaxiPark Object into JSON Format
 * Resource Links:
 * https://www.baeldung.com/apache-commons-csv
 * https://www.baeldung.com/kotlin/csv-files
 * https://www.baeldung.com/kotlin/json-convert-data-class
 *
 */
fun TaxiPark.toJSON(): String {
    val gson = Gson()
    return gson.toJson(this)
}

/**
 *
 */
fun TaxiPark.fromJSON(jsonTaxiPark: String): TaxiPark {
    val gson = Gson()
    return gson.fromJson(jsonTaxiPark, TaxiPark::class.java)
}


/**
 *
 */
fun readTaxiParkFromJSON(jsonTaxiPark: String): TaxiPark {
    val gson = Gson()
    return gson.fromJson(jsonTaxiPark, TaxiPark::class.java)
}


/**
 *
 */
fun Trip.display(): String {
    val discountText = discount?.let { ", $it" } ?: ""
    return "(${driver.lastName}:${driver.firstName}, ${passengers.map { (it.firstName to it.lastName) }}, $duration, $distance$discountText)"
}


/**
 *
 */
fun TaxiPark.display() = buildString {
    appendLine()
    appendLine("Taxi park:")
    appendLine("Drivers: ${allDrivers.map { (it.firstName to it.lastName) }}")
    appendLine("Passengers: ${allPassengers.map { (it.firstName to it.lastName) }}")
    appendLine("Trips: ${trips.map { it.display() }}")
}



