package kode.scanner.symboltable.impl

import kode.scanner.symboltable.SymbolTable
import kode.scanner.symboltable.SymbolTableException

class SymbolTableImpl: SymbolTable {
    private val map = emptyMap<Int, String>().toMutableMap()
    private var lastPosition = -1

    override fun insert(symbol: String): Int {
        lastPosition += 1
        map[lastPosition] = symbol

        return lastPosition
    }

    override fun get(position: Int): String {
        return map[position] ?: throw SymbolTableException("Position $position not found inside the Symbol Table")
    }
}
