
/**
 * Web Resources:
 * - https://docs.scala-lang.org/cheatsheets/index.html
 * - https://docs.scala-lang.org/overviews/core/string-interpolation.html
 * - https://docs.scala-lang.org/overviews/collections-2.13/maps.html
 */
class HelloWorld {
  def sayHello(): String = {
    "Hello World!!"
  }
}

/**
 *
 */
class GettingStarted {

  // Simple variable declaration
  var someVar = 10.4
  val someval = 5
  val someString: String = "This is a string" //Declaration with Explicit Type

  // Functions definition
  def f(x: Int): Int = { x*x }
  def sayHello(name: String) = {println("Hello " + name)}
  def simplePrint(): Unit = println("Hello World")

  def anonymousFunctions(): Unit = {
    // Simple inline function definition
    val inlineFunc = (x: Int) => x*x
    println(inlineFunc(2))
    // Inline function producing other function
    // for example zscore(0,1) produces a function that
    // computes zscores with mean=0, sd=1 for any x that is
    // zscore(0,1)(x)
    val zscore = (mean: Double, sd: Double) =>
                  (x: Double) => (x - mean) / sd
    println(zscore(0, 1)(3.4))
  }

  // Basic data strcutures
  def basicDataStructures(): Unit = {
    //Simple tuple
    val ntuple = (1, 2, 3, 4)
    println(ntuple)
    // Destructuring values
    val (w, x, y, z) = ntuple
    println(s"$w, $x, $y, $z") //USing string interpolation

    // List structures
    var someList = List(1,2,3)
    someList = 4 :: someList // List construction (CONS)
    println(someList :: List(1, 4 ,5)) // Nested List construction
    someList.foreach {println}
    someList.map {x:Int => x*x}.foreach {println}
    println(s"${someList(0)}, ${someList(2)}, ${someList(3)}")

    // Another ways to create a list
    var anotherList = 1 :: 2 :: 3 :: Nil
    println(anotherList)

    anotherList = List.range(0, 2, 10) //Using range
    println(anotherList)

    anotherList = List.fill(3)(0) // Using fill
    println(anotherList)

    anotherList = List.tabulate(10){n: Int => n*n}
    println(anotherList)

    println(anotherList.filter(_ % 2 == 0)) // Filter List operations

    println(List.tabulate[Double](10){n => 2.0*n})

    var sum = 0
    anotherList = List.tabulate(10){n => n}
    anotherList.foreach(sum += _)
    println(s"Sum of ${anotherList.mkString(", ")} is $sum")

    // Using Ranges
    println((1 to 20).filter(_ % 2 == 0))
    println((1 until( 20)).map(_ * 1))


    //
    val names = Vector("Bob", "Fred", "Joe", "Julia", "Kim")
    for(name <- names) println(name)


    // Array Structures
    val someArray = Array.tabulate[Double](10){n => n*2.0}
    println(someArray.mkString("[","-","]"))

    // Set Structures
    val someSet = Set(1, 1, 2, 3)
    println(someSet)

    // Map Structures
    val someMap: Map[String, String] = Map("France" -> "Paris", "US" -> "Washington", "Japan" -> "Tokyo")
    println(someMap("France"))
    println(someMap.keys)
    println(someMap.values)
  }


}

