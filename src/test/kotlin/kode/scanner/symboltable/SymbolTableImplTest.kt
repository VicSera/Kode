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
    fun `First insert has position 0`() {
        Assert.assertEquals(0, symbolTable.insert("test"))
    }

    @Test
    fun `Positions increment by 1`() {
        val pos1 = symbolTable.insert("a")
        val pos2 = symbolTable.insert("b")

        Assert.assertEquals(pos1 + 1, pos2)
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
            symbolTable.get(10)
            Assert.fail("Exception did not get thrown")
        } catch (_: SymbolTableException) {
            return
        } catch (_: Exception) {
            Assert.fail("Wrong exception got thrown")
        }
    }
}
