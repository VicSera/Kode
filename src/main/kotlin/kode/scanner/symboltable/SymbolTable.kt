package kode.scanner.symboltable

interface SymbolTable {
    /**
     * Insert the given symbol into the [SymbolTable]
     * @return the bucket and the position in the bucket withing the [SymbolTable] that the symbol was added in
     */
    fun insert(symbol: String): Pair<Int, Int>

    /**
     * Fetch the symbol from the given bucket on the given position in the [SymbolTable]
     * @return the found symbol
     */
    fun get(position: Pair<Int, Int>): String
}
