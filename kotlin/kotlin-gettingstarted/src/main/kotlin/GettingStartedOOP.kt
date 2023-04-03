import java.awt.Color

/**
 * Web Resources:
 * https://www.tutorialspoint.com/kotlin/kotlin_class_and_object.htm
 * https://kotlinlang.org/docs/classes.html
 * https://kotlinlang.org/docs/typecasts.html
 * https://kotlinlang.org/docs/extensions.html
 * https://kotlinlang.org/docs/object-declarations.html
 */

/**
 * Basic class creation
 */
class Computer(val name: String = "White box") //Class definition with Primary Constructor
class Cpu() { // Class definition using initialization block and Secondary Constructor
    var brand: String
    var speed: Int

    // Initializer block
    init {
        this.brand = "Default"
        this.speed = 166 // In Mhz
    }

    constructor(brand: String, speed: Int) : this() {
        this.brand = brand
        this.speed = speed
    }

    override fun toString(): String {
        return "Cpu( brand=$brand, speed=$speed )"
    }

}
//
fun exercise_obj1(){
    // Basic object instantiation
    val computer1 : Computer = Computer()
    println(computer1.name)

    // Using distinct contructors
    val cpu1: Cpu = Cpu()
    val cpu2: Cpu = Cpu(speed=33, brand="Intel")
    println(cpu1)
    println(cpu2)
}

/**
 * Using interfaces with Kotlin
 */
interface Player {
    fun play()
    fun fastForward()
    fun rewind()
}

class MusicPlayer : Player {
    override fun play() {
        println("Music is Playing >" )
    }

    override fun fastForward() {
        println("FF >> Music")
    }

    override fun rewind() {
        println("RWD << Music")
    }
}

class VideoPlayer : Player {
    override fun play() {
        println("Video is Playing >")
    }

    override fun fastForward() {
        println("FF >> Video")
    }

    override fun rewind() {
        println("RWD << Video")
    }

}
//
fun exercise_obj2(){
    val musicPlayer : MusicPlayer = MusicPlayer()
    val videPlayer : VideoPlayer  = VideoPlayer()
    val players : Array<Player> = arrayOf(musicPlayer, videPlayer)
    for (player : Player in players) player.play()
}


/**
 * Inheritance with Classes:
 * Kotlin superclass Any has three methods: equals(), hashCode(), and toString().
 * Thus, these methods are defined for all Kotlin classes.
 *
 * Everything in Kotlin is by default final, hence, we need to use
 * the keyword open in front of the class declaration to make it inheritable
 * for other classes. Kotlin uses operator ":" to inherit a class.
 *
 */
open class SomeParentClass(val name: String = "Default"){
    private   var privateProperty: Int = 1 //Visible only in current class scope
    protected open var protectedProperty: String = "Default Hello"
    internal  open var moduleProperty: Double = Math.PI
    var defaultPublicProperty: String = "Hello Public World!!!"
    //
    init {
        println("Init Base class.")
    }
    //
    override fun toString(): String {
        return "+ SomeParentClass(name=$name)"
    }
}

class SomeChildClass(name: String) : SomeParentClass(name) {
    init {
        println("Init Child Class.")
        this.protectedProperty = "Hello Protected property" //Visible only hierarchy classes
        this.defaultPublicProperty = "Hello Child public world"
    }

    override fun toString(): String {
        return super.toString() + "\n|--> SomeChildClass(name=$name)"
    }
}
// Note: Here are visible Public and Module properties
fun exercise_obj3() {
    val someObj1 = SomeChildClass("Eco 1")
    println(someObj1)
    println(someObj1.defaultPublicProperty)
}

/**
 * Abstract Classes
 */
abstract class Movement(){
    var name: String = "Default"
    //
    abstract fun left()
    abstract fun right()
}

class DeviceMovement : Movement() {
    init {
        this.name = "Custom Device movement"
    }
    override fun left() {
        println("Moving to the left!")
    }

    override fun right() {
        println("Moving to the right!")
    }
}
//
fun exercise_obj4(){
    val deviceMovement : DeviceMovement = DeviceMovement()
    println(deviceMovement.name)
    deviceMovement.left()
}

/**
 * Extensions:
 * Kotlin extensions provide the ability to extend a class with new functionality
 * without implementing the inheritance concept by a class or using design pattern
 * such as Decorator. These extensions basically add some functionality in an existing
 * class without extending the class.
 */
class BaseMessage(var message:String = "Default Message") {
    fun printMessage(){
        println(message)
    }
}
// Extension function:
// A kotlin extension function is a member function of a class,
// which is defined outside the class.
// The created extension functions are used as a regular function inside that class.
fun BaseMessage.printMessage2(){
    println("This is an enhanced Message: $message")
}

// Extension Properties:
// Extension properties are also defined outside of the class.
// Since extensions do not actually insert members into classes,
// there is no efficient way for an extension property to have a backing field.
// This is why initializers are not allowed for extension properties.
val BaseMessage.capitalizedMessage: String
    get() = message.replaceFirstChar { c -> c.uppercase() }

//
fun exercise_obj5(){
    val message : BaseMessage = BaseMessage()
    message.printMessage2()
    //
    message.message = "hello world"
    println(message.capitalizedMessage)

}

/**
 * Especial type of classes
 */
// Data Classes
data class Employee(val name:String, val department: String)
fun exercise_obj6(){
    // Structured Data class
    val employee1: Employee = Employee("John Doe", "IT")
    println(employee1)
    // Destructuring values from data class
    val (name, department) = employee1
    println("Employee(name=$name, department=$department)")
}

// Pair and Triple classes
fun exercise_obj7(){
    val pair1 = Pair('a', 1)
    val pair2 = 'b' to 2
    //
    println(pair1)
    println("(${pair1.first}, ${pair1.second})")
    println(pair2)
    // Destructuring pair class
    val (v1, v2) = pair2
    println("$v1 to $v2")

    // Using pair for map creation
    val map1 = mapOf(Pair('a', 1), Pair('b',2))
    val map2 = mapOf('a' to 1, 'b' to 2)
    for ((key, value) in map1) println("$key : $value")
    for ((key, value) in map2) println("$key : $value")
    //
    val point3D: Triple<Int, Int, Int> = Triple(1, 2, 3)
    println(point3D)
}

//Enumeration classes
enum class Colors {GREEN, YELLOW, ORANGE, RED } // Basic Enumeration Class
enum class PizzaSize(val sizeInCm: Int) {       // Valued Enumeration Class
    SMALL(15),
    MEDIUM(20),
    LARGE(25),
    EXTRALARGE(30)
}
fun exercise_obj8() {
    fun select_color(color: Colors) {
        when(color){
            Colors.GREEN  -> println("You selected ${Colors.GREEN}")
            Colors.YELLOW -> println("You selected ${Colors.YELLOW}")
            Colors.ORANGE -> println("You selected ${Colors.ORANGE}")
            Colors.RED    -> println("You selected ${Colors.RED}")
        }
    }
    //
    select_color(Colors.YELLOW)
    // Iterate over enum
    for(color in Colors.values()) {
        println("value=$color, ordinal=${color.ordinal}")
    }
    //
    println("${PizzaSize.MEDIUM} is ${PizzaSize.MEDIUM.sizeInCm} cm") // MEDIUM is 20 cm
    println("${PizzaSize.EXTRALARGE} is ${PizzaSize.EXTRALARGE.sizeInCm} cm") // EXTRALARGE is 30 cm

}

/**
 *
 */
fun main(args: Array<String>) {
    exercise_obj8()
}