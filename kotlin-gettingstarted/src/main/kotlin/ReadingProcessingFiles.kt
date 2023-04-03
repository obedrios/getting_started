import org.apache.commons.csv.CSVFormat
import java.io.File

/**
 * Web Resources
 * https://www.baeldung.com/kotlin/read-file
 * https://www.baeldung.com/kotlin/csv-files
 * https://www.baeldung.com/kotlin/json-convert-data-class
 * https://commons.apache.org/proper/commons-csv/user-guide.html
 */


/**
 * Reading file from project resources
 */
fun file_reading_example(){
    val file: File = File(ClassLoader.getSystemResource("data/mtcars.csv").path)
    print(file.exists())
    file.readLines().forEach { println(it) }
}


/**
 * Reading CSV Files from resources as InputStream using Apache Commons
 */

// Data Class for mtcars.csv resource file
data class MtCar (
    val mpg: Float,
    val cyl: Int,
    val disp: Float,
    val hp: Int,
    val drat: Float,
    val wt: Float,
    val qsec: Float,
    val vs: Int,
    val am: Int,
    val gear: Int,
    val carb: Int
)
// Load records using csv file
fun file_csvreading_ex1(): List<MtCar> {
    val inputStreamReader = ClassLoader.getSystemResourceAsStream("data/mtcars.csv")?.reader()
    val mtcars: List<MtCar> = CSVFormat.Builder.create(CSVFormat.DEFAULT).apply {
        setIgnoreSurroundingSpaces(true)
    }.build().parse(inputStreamReader).drop(1).map {
        MtCar (
            mpg = it[0].toFloat(),
            cyl = it[1].toInt(),
            disp = it[2].toFloat(),
            hp = it[3].toInt(),
            drat = it[4].toFloat(),
            wt = it[5].toFloat(),
            qsec = it[6].toFloat(),
            vs = it[7].toInt(),
            am = it[8].toInt(),
            gear = it[9].toInt(),
            carb = it[10].toInt()
        )
    }
    //
    return mtcars
}

fun main(args: Array<String>) {
    val mtcars: List<MtCar> = file_csvreading_ex1()
    println("mtcars.csv contains ${mtcars.size} records")
    println("===== mtcars.csv contain the following records =====")
    mtcars.forEach { println(it.toString()) }
    //
    // Common operations on Collections
    println("=====")
    mtcars.filter { it.gear == 4}.take(3).forEach { println(it.toString()) }

    println("=====")
    println(mtcars.any{it.hp < 100})
    println(mtcars.find{it.hp < 100})

    println("=====")
    println(mtcars.filter { it.gear == 4 }.map { it.hp }.average())

    println("=====")
    println(mtcars.groupBy { it.gear }.size)
    mtcars.groupBy { it.gear }.forEach{ println(it.value.map{it.hp}.average())}

    println("=====")
    val mapByGear: Map<Int, List<MtCar>> = mtcars.groupBy { it.gear }
    val (gear, group) = mapByGear.maxBy { (_, group) -> group.size }
    println(gear)


}