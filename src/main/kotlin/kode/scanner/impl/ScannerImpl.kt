package kode.scanner.impl

import kode.scanner.Scanner
import kode.scanner.ScannerOutput
import java.io.File
import java.util.regex.Pattern

class ScannerImpl(tokenFile: File): Scanner {
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
            tokenize(line)
        }
        throw NotImplementedError()
    }

    private fun tokenize(line: String): List<String> {
        throw NotImplementedError()
    }
}
