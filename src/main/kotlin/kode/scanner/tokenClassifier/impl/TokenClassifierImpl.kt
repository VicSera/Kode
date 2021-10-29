package kode.scanner.tokenClassifier.impl

import kode.scanner.tokenClassifier.TokenClass
import kode.scanner.tokenClassifier.TokenClassifier
import kode.util.regexEscaped
import kode.util.toRegexStringWithOr

class TokenClassifierImpl(private val tokens: Set<String>): TokenClassifier {
    override fun classify(token: String): TokenClass? {
        return if (isStandalone(token))
            TokenClass.STANDALONE
        else if (isConstant(token))
            TokenClass.CONSTANT
        else if (isIdentifier(token))
            TokenClass.IDENTIFIER
        else
            null
    }

    private fun isIdentifier(token: String): Boolean {
        return "[a-zA-Z_]([a-zA-Z0-9_])*".toRegex().matches(token)
    }

    private fun isConstant(token: String): Boolean {
        return isInteger(token) || isChar(token) || isString(token) || isReal(token)
    }

    private fun isStandalone(token: String): Boolean {
        return tokens.regexEscaped().toRegexStringWithOr().toRegex().matches(token)
    }

    private fun isInteger(token: String): Boolean {
        return "[+-]?[0-9]+".toRegex().matches(token)
    }

    private fun isChar(token: String): Boolean {
        return "'[^']'".toRegex().matches(token)
    }

    private fun isString(token: String): Boolean {
        return "\"[^\"]*\"".toRegex().matches(token)
    }

    private fun isReal(token: String): Boolean {
        return "r([0-9])+|r?[0-9]+[.][0-9]+".toRegex().matches(token)
    }
}
