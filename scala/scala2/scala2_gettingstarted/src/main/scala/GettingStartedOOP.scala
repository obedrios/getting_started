import java.util


/**
 * Web Resources:
 * - https://docs.scala-lang.org/cheatsheets/index.html
 * - https://docs.scala-lang.org/scala3/book/domain-modeling-tools.html#auxiliary-constructors
 * - https://docs.scala-lang.org/tour/classes.html
 * - http://dcapwell.github.io/scala-tour/
 */

// Basic class definition with primary constructor
class SimpleDefinition(val name: String)

// Class definition with auxiliary constructor
class Book(var name:String = "Default Name", var pages:Int = 100){
  private var author: String = "Default Author"

  //Auxiliary constructor
  def this(name: String, pages: Int, author: String) = {
    this(name, pages)
    this.author = author
  }

  override def toString: String = s"Book(name=$name, pages=$pages, author=$author)"
}


/**
 * Hierarchy abstract class with factory pattern
 */
abstract class Player(val name: String) {
  def play(): Unit
  override def toString: String = s"Player(name=$name)"
}

object Player {
  def create(playertype: String, name: String, model: String): Player = {
    playertype match {
      case "MusicPlayer" => new MusicPlayer(name, model)
      case "VideoPlayer" => new VideoPlayer(name, model)
      case _ => throw new IllegalArgumentException
    }
  }
}

class MusicPlayer(name: String, model: String) extends Player(name: String) {
  override def play(): Unit = println(s"Playing Music $name, $model")
  override def toString: String = s"MusicPlayer(name=$name, $model)"
}

class VideoPlayer(name:String, model: String) extends Player(name: String){
  override def play(): Unit = println(s"Playing Video $name, $model")
  override def toString: String = s"VideoPlayer(name=$name, $model)"
}

/**
 * Minimal Observer pattern for demonstrate the use of traits (as interfaces)
 */
trait Observer {
  def update(observable: Observable, obj: Any): Unit
}

abstract class Observable {
  private var observerList = new util.LinkedList[Observer]
  def addObserver(observer: Observer): Boolean = observerList.add(observer)
  def countObservers(): Int = observerList.size
  def notifyObservers(obj: Any): Unit = observerList.forEach { _.update(this, obj) }
}

// Observable class
class Topic(name: String) extends Observable {
  private[this] var _currentMessage: String = ""

  def currentMessage: String = _currentMessage
  def currentMessage_=(value: String): Unit = {
    _currentMessage = value
    notifyObservers(value)
  }

  override def toString: String = s"Topic(name=$name)"
}

// Observer class
class MonitorTopic extends Observer {
  override def update(observable: Observable, obj: Any): Unit =
           println(s"Something Changes: ${observable}, $obj")
}

/**
 * Case Classes Demonstration
 */
case class Employee(name: String = "Jhon Doe", id: Int = 9999 )

/**
 *
 */
class GettingStartedOOP(){
  private val bookList = List(new Book(),
                         new Book("John Doe", 250),
                         new Book("John Doe", 250, author = "Jane Doe"))
  bookList.foreach(println)
  // Abstract Class Demo
  private val playersList = List(
    Player.create("MusicPlayer", "Onyx", "AS2340"),
    Player.create("VideoPlayer", name="SuperHD", model="HD180BX")
  )
  playersList.foreach {x: Player => println(x); x.play()}
  // Minimal Observer pattern for Traits and abstract Demonstration
  val topic: Topic = new Topic("Default Topic")
  val monitorTOpic: MonitorTopic = new MonitorTopic()
  topic.addObserver(monitorTOpic)
  topic.currentMessage = "Hello World"
  // Case Clases (Like Data Classes in other Languages)
  val employeeList = List(Employee(), Employee("Jane Doe", 7777))
  println(employeeList)
}




