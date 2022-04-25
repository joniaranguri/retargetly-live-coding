/*
*
* Letters and words
* Given two arrays one with letters and another with words,
* create  a function that returns the words that can be written
* with the letters available in the first array.
* Example:
*
* letters = ["h", "e", "l", "o", "c", "f", "f", "x", "y", "e"]
* words = ["hello", "coffee", "ley", "xyz"]
*
* Expected result: "coffee", "ley"
* */

val letters = listOf("h", "e", "l", "o", "c", "f", "f", "x", "y", "e")
val words = listOf("hello", "coffee", "ley", "xyz")

fun main(args: Array<String>) {
    processWords(words, letters).forEach { println(it) }
}

fun processWords(words: List<String>, letters: List<String>): MutableList<String> {
    val lettersHashMapCounter = mutableMapOf<Char, Int>()
    val results = mutableListOf<String>()
    letters transformInto lettersHashMapCounter

    for (word in words) {
        val myCurrentLettersMap = mutableMapOf<Char, Int>()
        for (charIndex in word.indices) {
            val currentChar = word[charIndex]
            myCurrentLettersMap add currentChar
            val currentLettersPair = Pair(currentChar, myCurrentLettersMap)
            if (currentLettersPair hasSameLetterCounterThan lettersHashMapCounter) {
                if (charIndex == word.lastIndex) {
                    results.add(word)
                }
            } else break
        }
    }
    return results
}

infix fun List<String>.transformInto(lettersHashMapCounter: MutableMap<Char, Int>) {
    letters.forEach { lettersHashMapCounter add it[0] }
}

infix fun Pair<Char, MutableMap<Char, Int>>.hasSameLetterCounterThan(otherMap: MutableMap<Char, Int>): Boolean {
    val charToValidate = this.first
    return otherMap[charToValidate]?.let { otherMapValue ->
        this.second[charToValidate]?.let { it <= otherMapValue }
    } ?: false
}

infix fun MutableMap<Char, Int>.add(charLetter: Char) {
    this[charLetter] = this[charLetter]?.let { it + 1 } ?: 1
}
