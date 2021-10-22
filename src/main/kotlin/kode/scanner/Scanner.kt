package kode.scanner

import java.io.File

interface Scanner {
    fun scan(file: File): ScannerOutput
}
