import kode.scanner.impl.ScannerImpl
import kode.scanner.pif.print
import kode.scanner.programInternalForm
import kode.scanner.symbolTable
import java.io.File

fun main() {
    val scanner = ScannerImpl(File("tokens.in"))

    val res = scanner.scan(File("input/ex1.kode"))
    res.programInternalForm.print()
    res.symbolTable.print()
}
