import kode.scanner.impl.ScannerImpl
import java.io.File

fun main() {
    val scanner = ScannerImpl(File("tokens.in"))

    scanner.scan(File("ex1.kode"))
}
