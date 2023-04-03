package minipractices.taxipark

import com.google.gson.Gson
import minipractices.taxipark.RegisteredUserType.RegisteredUserType

import scala.io.Source
import scala.util.Random

/**
 * Web Resources:
 * - https://www.baeldung.com/scala/random-number
 * - https://www.baeldung.com/scala/create-list
 * - https://docs.scala-lang.org/tour/pattern-matching.html
 * - https://x-stream.github.io/tutorial.html
 * - https://alvinalexander.com/scala/serializing-deserializing-xml-scala-classes/
 * - https://alvinalexander.com/scala/how-to-create-json-strings-from-scala-objects/
 * - https://alvinalexander.com/scala/implicit-extension-methods-functions-scala-2-3-dotty/
 */
class TaxiParkUtil {

  /**
   *
   * @param userType
   * @param qty
   * @return
   */
  def generateRegisteredUserSet(userType: RegisteredUserType, qty: Int = 10): Set[RegisteredUser] = {
    val lastNames = Source.fromResource("lastnames1000.txt").getLines().toList
    val personNames = Source.fromResource("names100.txt").getLines().drop(1)
      .map(line => line.split("\t")).toList
    //
    Set.fill[RegisteredUser](qty) {
      val lastName = lastNames(Random.nextInt(lastNames.size))
      val firstName = personNames(Random.nextInt(personNames.size))(Random.nextInt(1))
      val age = Random.between(20, 55)
      val id = Random.nextInt(9999)
      //
      RegisteredUser.create(userType, firstName, lastName, age, id)
    }
  }


  /**
   *
   * @param driverQty
   * @param passengerQty
   * @param maxTripQty
   * @param maxPassengersPerTip
   * @return
   */
  def generateTaxiParkDataSet(driverQty: Int = 10,
                              passengerQty: Int = 10,
                              maxTripQty: Int = 20,
                              maxPassengersPerTip: Int = 6): TaxiPark = {
    // Generate Registered Drivers List
    val registeredDrivers: Set[Driver] =
      generateRegisteredUserSet(RegisteredUserType.DRIVER, driverQty).asInstanceOf[Set[Driver]]
    // Generate Registered Passengers List
    val registeredPassengers: Set[Passenger] =
      generateRegisteredUserSet(RegisteredUserType.PASSENGER, passengerQty).asInstanceOf[Set[Passenger]]
    // Generate Trip List per Driver
    val trips = List.fill(maxTripQty) {
      // Generate Passengers List
      val tripPassengers = Set.fill(Random.nextInt(maxPassengersPerTip)) {
        registeredPassengers.toList(Random.nextInt(registeredPassengers.size))
      }
      // Create Trip Object
      Trip(
        registeredDrivers.toList(Random.nextInt(registeredDrivers.size)),
        tripPassengers,
        Random.nextInt(45),
        Random.nextInt(15),
        Random.between(0.0, 0.15)
      )
    }
    //
    TaxiPark(registeredDrivers, registeredPassengers, trips)
  }


  /**
   *
   * @param trip
   * @return
   */
  def wrapTrip(trip: Trip): TripWrapper =
    TripWrapper(trip.driver, trip.passengers.toArray, trip.duration, trip.distance, trip.discount)


  /**
   *
   * @param taxiPark
   * @return
   */
  def wrapTaxiPark(taxiPark: TaxiPark): TaxiParkWrapper =
    TaxiParkWrapper(taxiPark.allDrivers.toArray,
                    taxiPark.allPassengers.toArray,
                    taxiPark.trips.map( trip => wrapTrip(trip)).toArray)


  /**
   *
   * @param trip
   * @return
   */
   def unWrapTrip(trip: TripWrapper): Trip =
      Trip(trip.driver, trip.passengers.toSet, trip.duration, trip.distance, trip.discount)


  /**
   *
   * @param taxiPark
   * @return
   */
  def unWrapTaxiPark(taxiPark: TaxiParkWrapper): TaxiPark = {
    TaxiPark(taxiPark.allDrivers.toSet,
             taxiPark.allPassengers.toSet,
             taxiPark.trips.map(trip => unWrapTrip(trip)).toList)
  }


  /**
   *
   * @param taxiPark
   * @return
   */
  def taxiParkToJSON(taxiPark: TaxiPark): String  = {
    val gson = new Gson()
    gson.toJson(wrapTaxiPark(taxiPark))
  }

  /**
   *
   * @param taxiParkJson
   * @return
   */
  def taxiParkFromJSON(taxiParkJson: String): TaxiPark = {
    val gson = new Gson()
    val wrappedTaxiPark: TaxiParkWrapper = gson.fromJson(taxiParkJson,
                                           Class.forName("minipractices.taxipark.TaxiParkWrapper"))
    unWrapTaxiPark(wrappedTaxiPark)
  }



  //
}