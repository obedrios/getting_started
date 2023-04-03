package functionalprog

import scala.math._

object ProgFunExamples1 {

  // High order functions with anonymous functions
  // usage example: List.range(-3,3).map(x=> stdnorm(x.toDouble))
  def normdist(mu: Double, sigma:Double): (Double => Double) = {
    (x: Double) => (1.0/(sigma*sqrt(2.0*Pi)))*exp(-0.5*pow((x - mu)/sigma, 2))
  }

  // High order function example for normdist
  def stdnorm_example(): Unit = {
    val stdnorm = normdist(0,1)
    println(List.range(-3,4).map(x => stdnorm(x.toDouble)))
  }


  def funclist_example(): Unit = {
    val fsum = (x:Int, y:Int) => x + y
    val fsub = (x:Int, y:Int) => x - y
    val flist = List(fsum, fsub)
    //
    val results = flist.map(f => f(1,2))
    println(results)
  }

  // Implementation of gcd using Euclid's Algorithm
  def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a %b)

  // Recursion in factorial creates a huge calling tree
  // We may use tail recursion to reuse the stack
  // def factorial2(n: Int):BigInt =
  //   if (n == 0) 1 else n * factorial2(n - 1)
  // 
  def factorial(n: Int): BigInt = {
    def loop(acc: BigInt, n: Int): BigInt =
      if (n == 0) acc
      else loop(acc*n, n - 1)
    loop(1, n)
  }


  // Binomial Coefficient
  def choose(n:Int, k:Int): BigInt =
    factorial(n)/(factorial(k)*factorial(n-k))


  def pascal(row: Int): List[BigInt]  =
    List.range(0, row + 1).map(n => choose(row, n))

  // def balance(chars: List[Char]): Boolean = ???

}

