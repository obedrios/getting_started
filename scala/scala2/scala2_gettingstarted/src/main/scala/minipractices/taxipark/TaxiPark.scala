package minipractices.taxipark

import minipractices.taxipark.RegisteredUserType.RegisteredUserType

 abstract class RegisteredUser {
  val firstName: String
  val lastName: String
  val age: Int
  val id: Int
}

object RegisteredUser {
  def create(userType: RegisteredUserType,
             firstName: String,
             lastName: String,
             age: Int,
             id: Int): RegisteredUser = {
    userType match {
      case RegisteredUserType.DRIVER => Driver(firstName, lastName, age, id)
      case RegisteredUserType.PASSENGER => Passenger(firstName, lastName, age, id)
    }
  }
}


case class Driver(
   override val firstName: String,
   override val lastName: String,
   override val age: Int,
   override val  id: Int) extends RegisteredUser{
}


case class Passenger(
   override val firstName: String,
   override val lastName: String,
   override val age: Int,
   override val  id: Int) extends RegisteredUser {
}


// https://www.baeldung.com/scala/enumerations
object RegisteredUserType extends Enumeration {
  type RegisteredUserType = Value
  val PASSENGER, DRIVER = Value
}


case class Trip (
    driver: Driver,
    passengers: Set[Passenger],
    duration: Int,
    distance: Double,
    discount: Double) {
  val cost: Double = (1 - discount)*(duration + distance)
}


case class TaxiPark(
   allDrivers: Set[Driver],
   allPassengers: Set[Passenger],
   trips: List[Trip])


case class TripWrapper (
     driver: Driver,
     passengers: Array[Passenger],
     duration: Int,
     distance: Double,
     discount: Double) {
      val cost: Double = (1 - discount) * (duration + distance)
}


case class TaxiParkWrapper (
   allDrivers: Array[Driver],
   allPassengers: Array[Passenger],
   trips: Array[TripWrapper])

