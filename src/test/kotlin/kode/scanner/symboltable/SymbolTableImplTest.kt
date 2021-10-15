package kode.scanner.symboltable

import kode.scanner.symboltable.impl.SymbolTableImpl
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SymbolTableImplTest {
    private var symbolTable = SymbolTableImpl()

    @Before
    fun init() {
        symbolTable = SymbolTableImpl()
    }

    @Test
    fun `Get returns symbol on given position`() {
        val pos1 = symbolTable.insert("a")
        val pos2 = symbolTable.insert("b")

        Assert.assertEquals("a", symbolTable.get(pos1))
        Assert.assertEquals("b", symbolTable.get(pos2))
    }

    @Test
    fun `Get with invalid position throws exception`() {
        try {
            symbolTable.get(Pair(10, 10))
            Assert.fail("Exception did not get thrown")
        } catch (_: SymbolTableException) {
            return
        } catch (_: Exception) {
            Assert.fail("Wrong exception got thrown")
        }
    }
}
