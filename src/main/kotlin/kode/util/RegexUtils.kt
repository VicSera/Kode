package kode.util

fun Collection<String>.toRegexStringWithOr(): String {
    return this.reduce { acc, elem -> "$acc|$elem" }
}

fun Collection<String>.regexEscaped(): Collection<String> = this.map { Regex.escape(it) }
