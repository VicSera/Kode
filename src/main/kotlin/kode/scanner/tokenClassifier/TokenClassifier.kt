package kode.scanner.tokenClassifier

interface TokenClassifier {
    fun classify(token: String): TokenClass?
}
