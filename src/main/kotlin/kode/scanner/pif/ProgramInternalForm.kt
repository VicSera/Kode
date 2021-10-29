package kode.scanner.pif

typealias SymbolTablePosition = Pair<Int, Int>
typealias PifEntry = Pair<String, SymbolTablePosition>
typealias ProgramInternalForm = MutableList<PifEntry>

val SymbolTablePosition.bucket
    get() = first

val SymbolTablePosition.rank
    get() = second

fun emptyPosition() = SymbolTablePosition(-1, -1)

val PifEntry.token
    get() = first

val PifEntry.stPos
    get() = second

fun ProgramInternalForm.print() {
    println("PIF:")
    println("Token | (Bucket: Rank)")
    println("----------------------")
    this.forEach { entry ->
        println("${entry.token} | (${entry.stPos.bucket}, ${entry.stPos.rank})")
    }
}
