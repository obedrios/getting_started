package minipractices.taxipark

import jdk.jshell.spi.ExecutionControl.NotImplementedException

object TaxiParkQueries {
  implicit class TaxiParkQueries(taxiPark: TaxiPark) {

    def findDriversWithNoTrips(): Set[Driver] = {
      //taxiPark.allDrivers.filter(driver => taxiPark.trips.filter(trip => trip.driver == driver).isEmpty)
      taxiPark.allDrivers.filter(driver => !taxiPark.trips.exists(trip => trip.driver == driver))
    }


    def findTripsWithNoPassengers(): Set[Trip] = {
      taxiPark.trips.filter( trip => trip.passengers.isEmpty).toSet
    }

  }
}
