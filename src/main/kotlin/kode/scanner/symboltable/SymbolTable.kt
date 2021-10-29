package kode.scanner.symboltable

import kode.scanner.pif.SymbolTablePosition

interface SymbolTable {
    /**
     * Insert the given symbol into the [SymbolTable]
     * @return the bucket and the position in the bucket withing the [SymbolTable] that the symbol was added in
     */
    fun insert(symbol: String): SymbolTablePosition

    /**
     * Fetch the symbol from the given bucket on the given position in the [SymbolTable]
     * @return the found symbol
     */
    fun get(position: SymbolTablePosition): String

    /**
     * Get the bucket corresponding to the given symbol
     * @return the bucket number
     */
    fun getBucket(symbol: String): Int

    /**
     * Look for the given symbol in the specified bucket
     * @return the position of the symbol within the bucket, null if not found
     */
    fun findInBucket(symbol: String, bucket: Int): Int?

    /**
     * Prints the [SymbolTable]
     */
    fun print()
}
