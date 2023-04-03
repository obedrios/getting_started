

import minipractices.taxipark._
import minipractices.taxipark.TaxiParkQueries.TaxiParkQueries


/**
 * Getting started with Scala
 */
object Main {
  def main(args: Array[String]): Unit = {
    // Getting Started with Basic Scala
    //val gettingStarted = new GettingStarted
    //gettingStarted.basicDataStructures()

    // Object Oriented Programming with Scala
    // val gettingStartedOOP = new GettingStartedOOP

    //val gettingStartedWithFileProcess = new GettingStartedWithFileProcess
    //gettingStartedWithFileProcess.process_csv_file2()

    val taxiParkUtil = new TaxiParkUtil()
    val taxiPark = taxiParkUtil.generateTaxiParkDataSet()

    //for(item <- taxiPark.findDriversWithNoTrips()) println(item)
    for(item <- taxiPark.findTripsWithNoPassengers()) println(item)


  }
  //
}