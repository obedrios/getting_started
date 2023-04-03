package minipractices.taxipark

data class TaxiPark(
        val allDrivers: Set<Driver>,
        val allPassengers: Set<Passenger>,
        val trips: List<Trip>)

abstract class RegisteredUser {
    abstract val firstName: String
    abstract val lastName: String
    abstract val age: Int
    abstract val id: Int
}

//data class Driver(val firstName: String, val lastName: String, val age: Int, val id: Int)
data class Driver(
    override val firstName: String,
    override val lastName: String,
    override val age: Int,
    override val  id: Int
): RegisteredUser()


//data class Passenger(val firstName: String, val lastName: String, val age: Int, val id: Int)
data class Passenger(
    override val firstName: String,
    override val lastName: String,
    override val age: Int,
    override val  id: Int
): RegisteredUser()


data class Trip(
        val driver: Driver,
        val passengers: Set<Passenger>,
        // the trip duration in minutes
        val duration: Int,
        // the trip distance in km
        val distance: Double,
        // the percentage of discount (in 0.0..1.0 if not null)
        val discount: Double? = null
) {
    // the total cost of the trip
    val cost: Double
        get() = (1 - (discount ?: 0.0)) * (duration + distance)
}