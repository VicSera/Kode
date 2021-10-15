package kode.scanner.symboltable

interface SymbolTable {
    /**
     * Insert the given symbol into the [SymbolTable]
     * @return the position in the [SymbolTable] that the symbol was added in
     */
    fun insert(symbol: String): Int

    /**
     * Fetch the symbol from the given position in the [SymbolTable]
     * @return the symbol from the given position
     */
    fun get(position: Int): String
}
