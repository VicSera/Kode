package kode.scanner.symboltable.impl

import kode.scanner.pif.SymbolTablePosition
import kode.scanner.pif.bucket
import kode.scanner.pif.rank
import kode.scanner.symboltable.SymbolTable
import kode.scanner.symboltable.SymbolTableException

class SymbolTableImpl: SymbolTable {
    private val buckets = Array(30) { emptyList<String>().toMutableList() }

    override fun insert(symbol: String): SymbolTablePosition {
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

    override fun get(position: SymbolTablePosition): String {
        return buckets.getOrNull(position.bucket)?.getOrNull(position.rank)
            ?: throw SymbolTableException("Position $position not found inside the Symbol Table")
    }

    override fun getBucket(symbol: String): Int {
        return hash(symbol)
    }

    override fun findInBucket(symbol: String, bucket: Int): Int? {
        return buckets[bucket]
            .mapIndexed { index, s -> Pair(index, s) }
            .find { it.second == symbol }
            ?.first
    }

    override fun print() {
        println("ST:")
        buckets.forEachIndexed { index, bucket ->
            if (bucket.isNotEmpty()) {
                val formattedBucket = bucket.mapIndexed { rank, symbol -> "($rank, $symbol)" }.reduce{ acc, str -> "$acc, $str" }
                println("Bucket: $index: $formattedBucket")
            }
        }
    }

    private fun hash(symbol: String): Int {
        var sum = 0
        symbol.forEach { c ->
            sum += c.code
        }
        return sum % 30
    }
}
