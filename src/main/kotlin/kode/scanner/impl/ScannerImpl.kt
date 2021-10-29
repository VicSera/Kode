package kode.scanner.impl

import kode.scanner.Scanner
import kode.scanner.ScannerOutput
import kode.scanner.pif.PifEntry
import kode.scanner.pif.ProgramInternalForm
import kode.scanner.pif.SymbolTablePosition
import kode.scanner.pif.emptyPosition
import kode.scanner.pif.rank
import kode.scanner.symboltable.SymbolTable
import kode.scanner.symboltable.impl.SymbolTableImpl
import kode.scanner.tokenClassifier.TokenClass
import kode.scanner.tokenClassifier.TokenClassifier
import kode.scanner.tokenClassifier.impl.TokenClassifierImpl
import kode.util.toRegexStringWithOr
import java.io.File

class ScannerImpl(
    tokenFile: File
): Scanner {
    lateinit var tokens: Set<String>
        private set
    private var tokenClassifier: TokenClassifier

    init {
        initTokens(tokenFile)
        tokenClassifier = TokenClassifierImpl(tokens)
    }

    override fun scan(file: File): ScannerOutput {
        val pif: ProgramInternalForm = emptyList<PifEntry>().toMutableList()
        val st: SymbolTable = SymbolTableImpl()

        file.forEachLine { line ->
            val tokens = tokenize(line)

            tokens.forEach { token ->
                when (tokenClassifier.classify(token)) {
                    TokenClass.STANDALONE -> pif.add(PifEntry(token, emptyPosition()))
                    TokenClass.CONSTANT -> addToPifAndSt(token, "constant", pif, st)
                    TokenClass.IDENTIFIER -> addToPifAndSt(token, "identifier", pif, st)
                }
            }
        }

        return ScannerOutput(pif, st)
    }

    private fun addToPifAndSt(token: String, tokenClassName: String, pif: ProgramInternalForm, st: SymbolTable) {
        val bucket = st.getBucket(token)
        val rank = st.findInBucket(token, bucket) ?: st.insert(token).rank
        pif.add(PifEntry(tokenClassName, SymbolTablePosition(bucket, rank)))
    }

    private fun tokenize(line: String): List<String> {
        val anySeparator = tokens.map {
            when(it) {
                "-" -> "-(?!>)"
                "=" -> "=(?!=)"
                "<" -> "<(?!=)"
                ">" -> ">(?!=)"
                "!" -> "!(?!=)"
                else -> Regex.escape(it)
            }
        }.toRegexStringWithOr()
        val alphanumericStringWithSomeSymbols = "[a-zA-Z0-9_\"]+"

        return "$alphanumericStringWithSomeSymbols|$anySeparator".toRegex().findAll(line).toList().map { it.value }
    }

    private fun initTokens(tokenFile: File) {
        val tmpTokens = mutableSetOf<String>()
        tokenFile.readLines().forEach { tmpTokens.addAll(it.split(' ')) }

        tokens = tmpTokens.toSet()
    }
}
