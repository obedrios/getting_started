import kotlin.random.Random

/**
 * Web Resources:
 * https://www.tutorialspoint.com/kotlin/index.htm
 */

fun add_integers(z1: Int, z2: Int): Int {
    return z1 + z2
}

fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}

//Is the same as hello_world(name: String): Unit
fun hello_world(name: String){
    println("Hello World $name")
}

fun main(args: Array<String>) {
    // Basic variable declaration
    val x = 3.0*Math.PI/4.0 //Read only variable
    var y = Math.sin(x)     //Read and Write variable
    println("The value of Sin($x) = $y")

    // Control flow structures in Kotlin
    var number = Random.nextInt(10)
    if (number < 5){
        println("The number $number is less than 5")
    } else {
        println("The number $number is greater than 5")
    }

    // An interesting way to use if
    println(if(number < 5) "Less than 5" else "Greater than 5")

    // When structure control flow without argument
    when {
        number == 0 -> println("Its Zero")
        number  < 5 -> println("Less than 5")
        else -> println("Thats it, other number")
    }

    // When structure control flow with argument
    when (number) {
        0 -> println("Its zero")
        5 -> println("Its five")
        else -> println("Another number")
    }

    // An interesting using of when structure
    var degrees = Random.nextInt(50)
    val (description, color) = when {
        degrees <  5 -> "cold" to "BLUE"
        degrees < 25 -> "mild" to "YELLOW"
        degrees < 30 -> "warm" to "ORANGE"
        else -> "hot" to "RED"
    }
    println("Temperature is $description with $color")


    //Looping using while
    while(number < 5){
        println("Number $number is less 5")
        number = Random.nextInt(10)
    }

    // Loop using for and ranges
    // https://kotlinlang.org/docs/ranges.html
    val numbersStrings = arrayOf("One", "Two", "Three", "Four")
    for (n in numbersStrings) print("$n, ")
    println("\nInclusive:")
    for (n in 1..10) print("$n ")
    println("\nNon-Inclusive:")
    for (n in 1 until 10) print("$n ")
    println("\nIterate in steps:")
    for (n in 1..10 step 2) print("$n ")
    println(" ")

    // Iterating over map
    val my_map = mapOf(1 to "one", 2 to "two", 3 to "three")
    for ((key, value) in my_map){
        println("$key : $value")
    }

    // Use the in operator and ranges to for checking belong collection
    if ('a' in 'a'..'z') println("a is in the range")
    if ("One" in numbersStrings) println("One is in the set")

    fun check_digit(c: Char) = when (c) {
        in '0'..'9' -> "It's a digit"
        in 'a'..'z' -> "It's a letter"
        else -> "Something else."
    }
    println(check_digit('0'))

    // Functions Everywhere: Top-level, Member Function, Local function
    fun inline_max(a: Int, b: Int): Int = if (a > b) a else b
    println("Verify max function: ${inline_max(5, 10)}")

    // Functions with default argumenrs
    fun another_function(a: Int = 2, b: Int = 3): Int = a * b
    println("Functions with default arguments: ${another_function()}")

    // Lambda functions
    val lambdafun1 = {x: Int, y: Int -> x + y} // this is the lambda syntax
    println("Lambda function result: ${lambdafun1(3,4)}")
    println("Is possible an anonymous lambda such ${{x: Double -> Math.cos(x)}(3.0*Math.PI/2.0)}")


    // Array Operations in Kotlin
    // https://www.geeksforgeeks.org/kotlin-array/
    println("Array Operations:")
    var numbers = Array(10){ i -> (i*i).toString()}
    for (n in numbers) print("$n ")
    println(" ")
    numbers.forEach { print("$it ") }



}