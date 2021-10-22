package kode.scanner

import kode.scanner.impl.ScannerImpl
import org.junit.Assert
import org.junit.Test
import java.io.File

class ScannerImplTest {
    private val scanner = ScannerImpl(File("tokens.in"))

    @Test
    fun `Tokens are initialized properly`() {
        Assert.assertEquals(33, scanner.tokens.size)
    }

    @Test
    fun `Separators are initialized properly`() {
        Assert.assertEquals(11, scanner.separators.size)
    }
}
