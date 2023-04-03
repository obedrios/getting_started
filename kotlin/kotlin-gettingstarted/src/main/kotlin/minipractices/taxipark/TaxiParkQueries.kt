package minipractices.taxipark


/**
 *
 */
fun TaxiPark.findDriversWithNoTrips(): Set<Driver> =
   allDrivers.filter { driver -> trips.none { it.driver == driver } }.toSet()


/**
 *
 */
fun TaxiPark.findTripsWithNoPassengers(): Set<Trip> =
   trips.filter { it.passengers.isEmpty()  }.toSet()


/**
 * Find all the clients who completed at least the given number of trips.
// Initial prototype
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> =
   trips.flatMap { it.passengers }
      .groupBy { it }
      .filter { it.value.size >= minTrips }
      .map { it.key }.toSet()
*/
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> =
   trips.flatMap(Trip::passengers)
      .groupBy { passenger -> passenger }
      .filterValues { group -> group.size >= minTrips }
      .keys

//
fun TaxiPark.findFaithfulPassengers2(minTrips: Int): Set<Passenger> =
   allPassengers.filter {
      passenger ->  trips.count { passenger in it.passengers } >= minTrips
   }.toSet()


/**
 * Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> =
   allPassengers.filter {
      passenger -> trips.count { driver == it.driver &&  passenger in it.passengers} > 1
   }.toSet()


/**
 * Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
   TODO()
}


/**
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
   TODO()
}

