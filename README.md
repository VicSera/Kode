# Kode
### A Kotlin-inspired miniature programming language

## `Scanner`
Implemented using `regex` and has two steps:
1. Tokenization
    - done using a long `regex` containing all the predefined tokens from the `tokens.in` file
    - special cases (`-` and `->`) are resolved using `regex` lookahead to make sure the tokens get correctly identified
2. Classification
   - uses a `TokenClassifier` to classify each token into a `TokenClass`, which is one of:
        * `PREDEFINED` - a predefined token from `tokens.in`
          * added to the `ProgramInternalForm` as-is
        * `CONSTANT` - a `string`/`char`/`int`/`real` constant
          * added to the `SymbolTable` if it doesn't already exist, and then to the `ProgramInternalForm` using the position (`bucket` and `rank` within the bucket) retrieved from the `SymbolTable`
        * `IDENTIFIER` - a user-defined identifier
          * the same as is the case for `CONSTANT`

