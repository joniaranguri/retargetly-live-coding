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
    val results = mutableListOf<String>()
    for (word in words) {
        val usedIndices = HashSet<Int>()
        for (characterIndex in word.indices) {
            val currentIndexOfLetters = getIndexOfCharacter(word[characterIndex], usedIndices)
            if (currentIndexOfLetters == -1) {
                break
            } else {
                if (characterIndex == word.lastIndex) {
                    results.add(word)
                }
                usedIndices.add(currentIndexOfLetters)
            }
        }

    }

    results.forEach {
        println(it)
    }

}

fun getIndexOfCharacter(character: Char, usedIndices: HashSet<Int>): Int {
    for (currentIndex in letters.indices) {
        if (character == letters[currentIndex][0] && !usedIndices.contains(currentIndex)) {
            return currentIndex
        }
    }
    return -1
}
