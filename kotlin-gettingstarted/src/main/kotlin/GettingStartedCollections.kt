import java.util.LinkedList
import kotlin.random.Random

/**
 *
 */

fun exercise_collections1(){
    // Create a basic list
    val itemsList = listOf("Item 1", "Item 2", "Item 3")
    // Iterate over list using for
    for (item in itemsList){
        println(item)
    }
    // Iterate over list using forech
    itemsList.forEach { println(it) }
    // Adding and removing items with + and - operators
    var newItemList = itemsList + "Item 4"
    println(newItemList.joinToString(","))
    newItemList = newItemList - "Item 1"
    println(newItemList.joinToString(","))

    val someString = "Hello World"
    println(someString.toCharArray().joinToString(",", prefix = "[", postfix = "]"))

    // Create list of numbers
    val numberList = List(size = 10) {i -> i*i}
    println(numberList.joinToString(",", prefix = "[", postfix = "]"))

    val randomNumbers = Array(size = 10) { Random.nextInt(50)}
    println(randomNumbers.joinToString(",", prefix = "[", postfix = "]"))

    val randomNumbersList = List(size = 10) {i -> (1..10).random()}
    println(randomNumbersList.joinToString(",", prefix = "[", postfix = "]"))
}


fun exercise_collections2(){
    val capitals = mapOf("USA" to "Washington", "Poland" to "Warsaw", "Ukraine" to "Kyiv")
    val alphabet: Map<Char, Int> = mapOf('A' to 1, 'B' to 2, 'C' to 3)
    println(capitals)
    // Iterate over map
    for (pair in alphabet) println("${pair.key} -> ${pair.value}")
    println(alphabet.keys)
    println(alphabet.values)
    // Query for key -> value
    println("${alphabet.get('A')}, ${alphabet['A']}, ${alphabet.get('D')}, ${'D' in alphabet}")
    //
    print(alphabet + ('D' to 4))// Sames as alphabet + Pair('D', 4)

}

data class Employeex(val name: String, val id: Int)

fun main(args: Array<String>){
    exercise_collections2()
}

