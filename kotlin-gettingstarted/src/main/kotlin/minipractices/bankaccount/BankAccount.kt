package minipractices.bankaccount

import java.util.*

enum class AccountType {debit, credit, checking}

/**
 *
 */
abstract class BankAccount(val accountType: AccountType) {
    var accountBalance = 0
    abstract fun deposit(money: Int)
    abstract fun withdraw(money: Int)

    override fun toString(): String {
        return "BankAccount(accountType = ${accountType.toString()}, accountBalance = $accountBalance)"
    }
}

/**
 *
 */
class CreditAccount(val startCreditLimit: Int): BankAccount(accountType = AccountType.credit){
    val creditLimit = startCreditLimit
    //
    init {
        this.accountBalance = creditLimit
    }

    override fun deposit(money: Int) {
        val checkCreditBalance = this.accountBalance + money
        if (checkCreditBalance > creditLimit){
            println("Cannot deposit $${money} more than your credit limit $${creditLimit}")
        } else {
            this.accountBalance += money
            println("Account deposit for $${money}, now you have $${accountBalance} " +
                    "of your credit limit $${creditLimit}")
        }
    }

    override fun withdraw(money: Int) {
        val checkCreditBalance = this.accountBalance - money
        if (checkCreditBalance >= 0) {
            this.accountBalance = this.accountBalance - money
            println("Account withdrawal for $${money}, now you have $${accountBalance} " +
                    "of your credit limit $${creditLimit}")
        } else {
            println("Not sufficient funds, $${money} > $${accountBalance}")
        }
    }

}

/**
 *
 */
class DebitAccount(val startBalance: Int = 0): BankAccount(accountType = AccountType.debit){

    init {
        this.accountBalance = startBalance
    }

    override fun deposit(money: Int) {
        this.accountBalance += money
        println("Account deposit for $${money}, now you have $${accountBalance}")
    }

    override fun withdraw(money: Int) {
        if (this.accountBalance == 0){
            println("Not sufficient funds, add funds to later withdraw")
        } else if (money > accountBalance){
            println("Not sufficient funds, ${money} > ${accountBalance}")
        } else {
            this.accountBalance = this.accountBalance - money
            println("Account withdrawal for $${money}, now you have $${accountBalance}")
        }
    }
}

/**
 *
 */
class CheckingAccount(startBalance: Int = 0) : BankAccount(accountType = AccountType.checking) {

    init {
        this.accountBalance = startBalance
    }

    override fun deposit(money: Int) {
        this.accountBalance += money
        println("Account deposit for $${money}, now you have $${accountBalance}")
    }

    override fun withdraw(money: Int) {
        if (this.accountBalance == 0) {
            println("Not sufficient funds, add funds to later withdraw")
        } else if (money > accountBalance) {
            println("Not sufficient funds, ${money} > ${accountBalance}")
        } else {
            this.accountBalance = this.accountBalance - money
            println("Account withdrawal for $${money}, now you have $${accountBalance}")
        }
    }

}

fun create_bankaccount(accountType: AccountType, startBalance: Int = 0): BankAccount {
    return when (accountType) {
        AccountType.credit   -> CreditAccount(startBalance)
        AccountType.debit    -> DebitAccount(startBalance)
        AccountType.checking -> CheckingAccount(startBalance)
    }
}

fun create_bankaccount_cmdui(){
    val strMainMenu = """      
       Welcome to your banking system.
       
       What type of account would you like to create?
       1. Debit account.
       2. Credit account.
       3. Checking account."""
    println(strMainMenu)
    //
    val input = Scanner(System.`in`)
    var userChoice = input.nextInt()
    when (userChoice) {
        1 -> create_bankaccount(AccountType.credit)
        2 -> create_bankaccount(AccountType.debit)
        3 -> create_bankaccount(AccountType.checking)
        else -> println("Invalid account type")
    }
}


fun main(args: Array<String>){
    val (a, b, c) = Triple(4, "y", listOf(null))
    println(a)
}