package kode.scanner.symboltable.impl

import kode.scanner.symboltable.SymbolTable
import kode.scanner.symboltable.SymbolTableException

class SymbolTableImpl: SymbolTable {
    private val buckets = Array(30) { emptyList<String>().toMutableList() }

    override fun insert(symbol: String): Pair<Int, Int> {
        val hashValue = hash(symbol)
        var pos = 0

        buckets[hashValue].forEach {
            if (it == symbol)
                return Pair(hashValue, pos)
            pos++
        }

        buckets[hashValue].add(symbol)

        return Pair(hashValue, buckets[hashValue].size - 1)
    }

    override fun get(position: Pair<Int, Int>): String {
        return buckets.getOrNull(position.first)?.getOrNull(position.second)
            ?: throw SymbolTableException("Position $position not found inside the Symbol Table")
    }

    private fun hash(symbol: String): Int {
        var sum = 0
        symbol.forEach { c ->
            sum += c.code
        }
        return sum % 30
    }
}
