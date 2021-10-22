package kode.scanner

import kode.scanner.pif.ProgramInternalForm
import kode.scanner.symboltable.SymbolTable

typealias ScannerOutput = Pair<ProgramInternalForm, SymbolTable>

val ScannerOutput.programInternalForm
    get() = first

val ScannerOutput.symbolTable
    get() = second
