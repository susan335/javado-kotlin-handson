package jp.watanave.githubsample.practice4

fun getString() : String? = "Kotlin"

fun getGreeter() : Greeter? = Greeter()

fun getAny() : Greeter = Greeter()

open class Greeter {
    open fun greet(): String = "Hello!"
}

fun hello(name: String?): String = "Hello, ${name ?: "World"}"