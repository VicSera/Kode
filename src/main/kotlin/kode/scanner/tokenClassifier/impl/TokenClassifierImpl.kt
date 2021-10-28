package kode.scanner.tokenClassifier.impl

import kode.scanner.tokenClassifier.TokenClass
import kode.scanner.tokenClassifier.TokenClassifier

class TokenClassifierImpl: TokenClassifier {
    override fun classify(token: String): TokenClass? {
        return if (isIdentifier(token))
            TokenClass.IDENTIFIER
        else if (isConstant(token))
            TokenClass.CONSTANT
        else if (isStandalone(token))
            TokenClass.STANDALONE
        else
            null
    }

    private fun isIdentifier(token: String): Boolean {
        return true
    }

    private fun isConstant(token: String): Boolean {
        return true
    }

    private fun isStandalone(token: String): Boolean {
        return true
    }
}
