package funtionalprog

import java.math.BigInteger


/**
 * Generate normal distribution numbers
 * returns function generate distribution numbers given (mu,sigma)
 */
fun normdist(mu: Double = 0.0, sigma: Double = 1.0) = { x:Double -> // Anonymous function block
    1.0/(sigma*Math.sqrt(2*Math.PI))*
    Math.exp(-0.5 * Math.pow(x - mu, 2.0) / Math.pow(sigma, 2.0))
}

// Implementation of gcd using Euclid's Algorithm
fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)


/**
 * Recursion in factorial creates a huge calling tree
 * fun factorial(n: Int): BigInteger =
 *   if (n == 1) n.toBigInteger() else n.toBigInteger()*factorial(n-1)
 *
 * We may use tail recursion to reuse the stack (see approach below)
 *
 * Web Resoources:
 * - https://www.programiz.com/kotlin-programming/recursion
 * - https://stackoverflow.com/questions/44287466/biginteger-in-kotlin
 *
 * Approach 1: Explicit Tail Recursion
 */
fun factorial_tailrec(n: Int): BigInteger {
    fun loop(acc: BigInteger, n: Int): BigInteger {
        return if (n == 0) acc
        else loop(acc * n.toBigInteger(), n - 1)
    }
    val factorialNumber =  loop((1).toBigInteger(), n)
    return factorialNumber
}

// Approach 2: Using Kotlin Tail Recursion optimization
tailrec fun factorial(n: Int, acc: BigInteger = (1).toBigInteger()): BigInteger =
    if (n == 1) acc else factorial(n - 1, acc*n.toBigInteger())

// Binomial Coefficient
fun choose(n: Int, k:Int) = factorial(n)/(factorial(k)* factorial(n-1))
