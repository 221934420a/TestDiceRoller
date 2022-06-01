
class Dice(val numSides:Int) {
    fun roll():Int{
        return (1..numSides).random()
    }
}

fun main() {
    val myFirstDice = Dice(6)
    println("size:${myFirstDice.numSides} roll:${myFirstDice.roll()}")
    val Dice2 = Dice(20)
    println("size:${Dice2.numSides} roll:${Dice2.roll()}")
}