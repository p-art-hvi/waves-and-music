# mp1 Feedback

## Grade: 3.5

| Compilation | Timeout | Duration |
|:-----------:|:-------:|:--------:|
|Yes|No|297.14|

## Test Results
### ca.ubc.ece.cpen221.mp1.Task3
| Test Status | Count |
| ----------- | ----- |
|skipped|0|
|failures|1|
|errors|0|
|tests|1|
#### Failed Tests
1. `testSimilarityGrouping(Entry)[1] (java.lang.NullPointerException)`
### ca.ubc.ece.cpen221.mp1.Task2
| Test Status | Count |
| ----------- | ----- |
|skipped|0|
|failures|0|
|errors|0|
|tests|2|
### ca.ubc.ece.cpen221.mp1.Task1
| Test Status | Count |
| ----------- | ----- |
|skipped|0|
|failures|0|
|errors|0|
|tests|11|

## Test Coverage
### SoundWaveSimilarity
| Metric | Coverage |
| ------ | -------- |
|LINE_MISSED|2|
|LINE_COVERED|0|
|BRANCH_MISSED|0|
|BRANCH_COVERED|0|
### SoundWave
| Metric | Coverage |
| ------ | -------- |
|LINE_MISSED|6|
|LINE_COVERED|184|
|BRANCH_MISSED|18|
|BRANCH_COVERED|72|

## Other Comments
Lots of code repetition or extremely long, complex methods. Specs incomplete, some have no @return.  Long values written out in multiple lines which should have been static constants. No inline comments in confusing methods. 

**minor: Method `checksLength` has a Cognitive Complexity of 6 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/ca/ubc/ece/cpen221/mp1/SoundWave.java`; lines `46` to `59`
**minor: Identical blocks of code found in 2 locations. Consider refactoring.**
file: `src/main/java/ca/ubc/ece/cpen221/mp1/SoundWave.java`; lines `352` to `353`
**minor: Method `checksWavesLength` has 26 lines of code (exceeds 25 allowed). Consider refactoring.**
file: `src/main/java/ca/ubc/ece/cpen221/mp1/SoundWave.java`; lines `69` to `100`
**minor: Identical blocks of code found in 2 locations. Consider refactoring.**
file: `src/main/java/ca/ubc/ece/cpen221/mp1/SoundWave.java`; lines `451` to `451`
**minor: Identical blocks of code found in 2 locations. Consider refactoring.**
file: `src/main/java/ca/ubc/ece/cpen221/mp1/SoundWave.java`; lines `454` to `454`
**minor: Method `contains` has a Cognitive Complexity of 21 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/ca/ubc/ece/cpen221/mp1/SoundWave.java`; lines `327` to `371`
**major: Avoid deeply nested control flow statements.**
file: `src/main/java/ca/ubc/ece/cpen221/mp1/SoundWave.java`; lines `357` to `359`
**minor: Method `similarity` has a Cognitive Complexity of 15 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/ca/ubc/ece/cpen221/mp1/SoundWave.java`; lines `381` to `458`
**minor: Method `contains` has 38 lines of code (exceeds 25 allowed). Consider refactoring.**
file: `src/main/java/ca/ubc/ece/cpen221/mp1/SoundWave.java`; lines `327` to `371`
**minor: File `SoundWave.java` has 271 lines of code (exceeds 250 allowed). Consider refactoring.**
file: `src/main/java/ca/ubc/ece/cpen221/mp1/SoundWave.java`; lines `1` to `459`
**minor: Similar blocks of code found in 2 locations. Consider refactoring.**
file: `src/main/java/ca/ubc/ece/cpen221/mp1/SoundWave.java`; lines `131` to `139`
**major: Identical blocks of code found in 3 locations. Consider refactoring.**
file: `src/main/java/ca/ubc/ece/cpen221/mp1/SoundWave.java`; lines `404` to `407`
**major: Identical blocks of code found in 3 locations. Consider refactoring.**
file: `src/main/java/ca/ubc/ece/cpen221/mp1/SoundWave.java`; lines `412` to `415`
**minor: Identical blocks of code found in 2 locations. Consider refactoring.**
file: `src/main/java/ca/ubc/ece/cpen221/mp1/SoundWave.java`; lines `90` to `97`
**minor: Identical blocks of code found in 2 locations. Consider refactoring.**
file: `src/main/java/ca/ubc/ece/cpen221/mp1/SoundWave.java`; lines `354` to `355`
**minor: Method `addEcho` has a Cognitive Complexity of 9 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/ca/ubc/ece/cpen221/mp1/SoundWave.java`; lines `227` to `248`
**minor: Identical blocks of code found in 2 locations. Consider refactoring.**
file: `src/main/java/ca/ubc/ece/cpen221/mp1/SoundWave.java`; lines `77` to `84`
**minor: Method `checksWavesLength` has a Cognitive Complexity of 12 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/ca/ubc/ece/cpen221/mp1/SoundWave.java`; lines `69` to `100`
**major: Method `similarity` has 52 lines of code (exceeds 25 allowed). Consider refactoring.**
file: `src/main/java/ca/ubc/ece/cpen221/mp1/SoundWave.java`; lines `381` to `458`
**major: Identical blocks of code found in 3 locations. Consider refactoring.**
file: `src/main/java/ca/ubc/ece/cpen221/mp1/SoundWave.java`; lines `423` to `426`
**minor: Similar blocks of code found in 2 locations. Consider refactoring.**
file: `src/main/java/ca/ubc/ece/cpen221/mp1/SoundWave.java`; lines `147` to `154`
**minor: Identical blocks of code found in 2 locations. Consider refactoring.**
file: `src/main/java/ca/ubc/ece/cpen221/mp1/utils/HelperMethods.java`; lines `36` to `40`
**minor: Method `helpDFT` has 5 arguments (exceeds 4 allowed). Consider refactoring.**
file: `src/main/java/ca/ubc/ece/cpen221/mp1/utils/HelperMethods.java`; lines `112` to `113`
**minor: Method `add` has a Cognitive Complexity of 14 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/ca/ubc/ece/cpen221/mp1/utils/HelperMethods.java`; lines `22` to `55`
**minor: Method `addEcho` has a Cognitive Complexity of 8 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/ca/ubc/ece/cpen221/mp1/utils/HelperMethods.java`; lines `67` to `95`
**minor: Method `add` has 28 lines of code (exceeds 25 allowed). Consider refactoring.**
file: `src/main/java/ca/ubc/ece/cpen221/mp1/utils/HelperMethods.java`; lines `22` to `55`
**minor: Identical blocks of code found in 2 locations. Consider refactoring.**
file: `src/main/java/ca/ubc/ece/cpen221/mp1/utils/HelperMethods.java`; lines `40` to `44`
**minor: Method `equals` has a Cognitive Complexity of 9 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/ca/ubc/ece/cpen221/mp1/utils/Pair.java`; lines `44` to `55`

## Checkstyle Results
### `SoundWave.java`
| Line | Column | Message |
| ---- | ------ | ------- |
| 9 | 1 | `Redundant import from the java.lang package - java.lang.Math.` |
| 11 | None | `Missing a Javadoc comment.` |
| 34 | 5 | `Missing a Javadoc comment.` |
| 46 | 31 | `'{' is not preceded with whitespace.` |
| 69 | 47 | `'{' is not preceded with whitespace.` |
| 189 | 33 | `'lchannel' hides a field.` |
| 189 | 33 | `Expected @param tag for 'lchannel'.` |
| 189 | 52 | `'rchannel' hides a field.` |
| 189 | 52 | `Expected @param tag for 'rchannel'.` |
| 206 | None | `Expected an @return tag.` |
| 270 | 52 | `Name 'RC' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 271 | 18 | `'rchannel' hides a field.` |
| 272 | 18 | `'lchannel' hides a field.` |
| 329 | 18 | `'rchannel' hides a field.` |
| 330 | 18 | `'lchannel' hides a field.` |
| 439 | 23 | `'0.0000000001' is a magic number.` |
| 447 | 23 | `'0.0000000001' is a magic number.` |
| 9 | 1 | `Redundant import from the java.lang package - java.lang.Math.` |
| 11 | None | `Missing a Javadoc comment.` |
| 34 | 5 | `Missing a Javadoc comment.` |
| 46 | 31 | `'{' is not preceded with whitespace.` |
| 69 | 47 | `'{' is not preceded with whitespace.` |
| 189 | 33 | `'lchannel' hides a field.` |
| 189 | 33 | `Expected @param tag for 'lchannel'.` |
| 189 | 52 | `'rchannel' hides a field.` |
| 189 | 52 | `Expected @param tag for 'rchannel'.` |
| 206 | None | `Expected an @return tag.` |
| 270 | 52 | `Name 'RC' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 271 | 18 | `'rchannel' hides a field.` |
| 272 | 18 | `'lchannel' hides a field.` |
| 329 | 18 | `'rchannel' hides a field.` |
| 330 | 18 | `'lchannel' hides a field.` |
| 439 | 23 | `'0.0000000001' is a magic number.` |
| 447 | 23 | `'0.0000000001' is a magic number.` |
### `SoundWaveSimilarity.java`
| Line | Column | Message |
| ---- | ------ | ------- |
| 4 | None | `Using the '.*' form of import should be avoided - ca.ubc.ece.cpen221.mp1.utils.*.` |
| 6 | None | `Missing a Javadoc comment.` |
| 21 | None | `Line is longer than 100 characters (found 101).` |
| 22 | None | `Comment matches to-do format 'TODO:'.` |
| 4 | None | `Using the '.*' form of import should be avoided - ca.ubc.ece.cpen221.mp1.utils.*.` |
| 6 | None | `Missing a Javadoc comment.` |
| 21 | None | `Line is longer than 100 characters (found 101).` |
| 22 | None | `Comment matches to-do format 'TODO:'.` |

