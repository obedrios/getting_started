

import org.apache.commons.csv.{CSVFormat, CSVRecord}

import java.util
import scala.collection.mutable.ListBuffer
import scala.io.Source
import scala.jdk.CollectionConverters.IterableHasAsScala
import scala.util.Using
import scala.util.control.Breaks.{break, breakable}

/**
 *  Web resources:
 *  - https://www.baeldung.com/scala/read-file-from-resources
 *  - https://alvinalexander.com/scala/how-to-open-read-text-files-in-scala-cookbook-examples/
 */
class GettingStartedWithFileProcess {

  // Read file from resources - Method 1
  def read_file_from_resource1(): Unit = {
    val resource = getClass.getResource("mtcars.csv")
    Using(Source.fromURL(resource)) {
      reader => for (line <- reader.getLines()) {
        println(line)
      }
    }
  }

  // Read file from resources - Method 2
  def read_file_from_resource2(): Unit = {
    val resource = Source.fromResource("mtcars.csv")
    val lines = resource.getLines()
    for(line <- lines) println(line)
  }

  // Read CSV - Method 1
  case class MtCar (mpg: Double, cyl: Int, disp: Double,
                    hp: Int, drat: Double, wt: Double, qsec: Double,
                    vs: Int, am: Int, gear: Int, carb: Int)
  def process_csv_file1(): util.LinkedList[MtCar] = {
    val mtcars = new util.LinkedList[MtCar]()
    //
    val resource = Source.fromResource("mtcars.csv")
    val lines = resource.getLines()
    var isHeader = true
    for(line <- lines){
      breakable {
        if (isHeader) {
          isHeader = !isHeader
          break
        }
        val record = line.split(",")
        val mtcar = MtCar(record(0).toDouble, record(1).toInt,    record(2).toDouble,
                          record(3).toInt, record(4).toDouble, record(5).toDouble,
                          record(6).toDouble, record(7).toInt, record(8).toInt,
                          record(9).toInt, record(10).toInt)
        mtcars.add(mtcar)
      }
    }
    //
    mtcars.forEach {println}
    mtcars
  }

  // Read CSV File - Method 2
  def process_csv_file2(): ListBuffer[MtCar] = {
    //val HEADERS = Array("mpg","cyl","disp","hp","drat","wt","qsec","vs","am","gear","carb")
    //val mtcars = new util.LinkedList[MtCar]()
    val mtcars = ListBuffer[MtCar]()
    val resource = Source.fromResource("mtcars.csv")
    val records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(resource.reader()).getRecords.asScala
    for (record <- records) {
      val mtCar = MtCar(
        record.get("mpg").toDouble, record.get("cyl").toInt,
        record.get("disp").toDouble, record.get("hp").toInt,
        record.get("drat").toDouble, record.get("wt").toDouble,
        record.get("qsec").toDouble, record.get("vs").toInt,
        record.get("am").toInt, record.get("gear").toInt,
        record.get("carb").toInt)
      //mtcars.add(mtCar)// println(mtCar)
      mtcars.append(mtCar)
    }
    //
    mtcars.foreach {
      println
    }
    mtcars
  }
}
