package kode.scanner.impl

import kode.scanner.Scanner
import kode.scanner.ScannerOutput
import kode.scanner.tokenClassifier.TokenClassifier
import java.io.File

class ScannerImpl(
    tokenFile: File,
    private val tokenClassifier: TokenClassifier
): Scanner {
    lateinit var tokens: Set<String>
        private set
    lateinit var separators: Set<String>
        private set

    init {
        initTokens(tokenFile)
    }

    private fun initTokens(tokenFile: File) {
        val tmpTokens = mutableSetOf<String>()
        tokenFile.readLines().forEachIndexed { index, line ->
            if (index == 2) // separators are on the third line of the tokens.in file
                separators = line.split(' ').toSet()
            else
                tmpTokens.addAll(line.split(' '))
        }

        tmpTokens.addAll(separators)
        tokens = tmpTokens.toSet()

        separators = separators.union(setOf(" "))
    }

    override fun scan(file: File): ScannerOutput {
        file.forEachLine { line ->
            val tokens = tokenize(line)
            println(tokens)
        }
        throw NotImplementedError()
    }

    private fun tokenize(line: String): List<String> {
        val anySeparator = tokens.map { Regex.escape(it) }.reduce { acc, token ->
            if (acc.isEmpty())
                token
            else
                "$acc|$token"
        }
        val alphanumericStringWithSomeSymbols = "[a-zA-Z0-9_\"]+"

        return "$alphanumericStringWithSomeSymbols|$anySeparator".toRegex().findAll(line).toList().map { it.value }
    }
}
