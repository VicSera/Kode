package kode.scanner.pif

typealias SymbolTablePosition = Pair<Int, Int>
typealias PifEntry = Pair<String, SymbolTablePosition>
typealias ProgramInternalForm = MutableList<PifEntry>

val SymbolTablePosition.bucket
    get() = first

val SymbolTablePosition.rank
    get() = second

val PifEntry.token
    get() = first

val PifEntry.stPos
    get() = second
