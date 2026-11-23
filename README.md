# Duzek

A small Kotlin/JVM library for adding Turkish dative suffixes to proper names.

```kotlin
Duzek.dative("Murat")  // Murat'a
Duzek.dative("Mehmet") // Mehmet'e
Duzek.dative("Ece")    // Ece'ye
```

## Status

Duzek is under development toward its first `0.1.0` release. The API may change before release.

## Why Duzek?

Turkish suffixes depend on pronunciation. Appending the same suffix to every name can produce incorrect text, such as `Murat'e` instead of `Murat'a`.

Duzek selects `-a` or `-e` using vowel harmony and inserts the buffer consonant `y` when the spoken form ends in a vowel.

## Usage

### Basic names

```kotlin
Duzek.dative("Murat")  // Murat'a
Duzek.dative("Selin")  // Selin'e
Duzek.dative("Arda")   // Arda'ya
Duzek.dative("Öykü")   // Öykü'ye
```

The returned value includes the name, an ASCII apostrophe (`'`), and the suffix.

### Explicit pronunciation

By default, Duzek calculates the suffix from the name's spelling. When spelling does not reflect pronunciation, provide a Turkish phonetic spelling through `pronunciation`:

```kotlin
Duzek.dative("Michael", pronunciation = "Maykıl") // Michael'a
Duzek.dative("George", pronunciation = "Corc")   // George'a
Duzek.dative("TRT", pronunciation = "te re te")   // TRT'ye
```

Both vowel harmony and the buffer consonant are determined from the supplied pronunciation. The output keeps the original name's spelling.

Pronunciation is caller-provided text, not IPA. Duzek does not automatically infer foreign pronunciations or expand abbreviations.

### Casing and whitespace

Turkish casing rules are used internally. Original casing is preserved, leading and trailing whitespace is removed, and internal whitespace is retained.

```kotlin
Duzek.dative("IŞIL")     // IŞIL'a
Duzek.dative(" Ece ")    // Ece'ye
Duzek.dative("Ali Can")  // Ali Can'a
```

### Invalid input

`dative` throws `IllegalArgumentException` when:

- The name is empty or blank.
- An explicitly supplied pronunciation is empty or blank.
- The form used for suffix calculation contains no Turkish vowel (`a, e, ı, i, o, ö, u, ü`).

```kotlin
Duzek.dative("")                         // throws
Duzek.dative("TRT")                      // throws: provide pronunciation
Duzek.dative("Michael", pronunciation = " ") // throws
```

These checks do not constitute full name validation. Inputs containing digits, punctuation, or existing suffixes are not comprehensively validated.

## Using Duzek in a multilingual app

Apply Duzek when formatting Turkish text. Keep complete sentences in your application's translation resources so each language can control word order.

For example, the translated message templates could be:

```text
Turkish: %1$s gönder
English: Send to %1$s
```

Pass `Duzek.dative(name)` to the Turkish template and the original name to the English template. Use the language of the resolved message template; the device language may differ from the app's selected language.

Duzek formats names; it does not choose locales or translate messages.

## Scope and limitations

- Only the dative case (`-a/-e`, `-ya/-ye`) is currently supported.
- The API is intended for bare proper names. Common nouns and names with existing suffixes are outside the current scope.
- Default suffix selection uses written vowels. Pronunciation-dependent exceptions require caller input; there is no built-in pronunciation dictionary.
- Names ending in a written consonant that is silent in pronunciation may need an explicit pronunciation.
- Numbers and abbreviations are not automatically pronounced or expanded.
- The current implementation targets Kotlin/JVM and uses `java.util.Locale`. It is not a Kotlin Multiplatform library.

## Installation

Package publication instructions and dependency coordinates will be added when a release is available.

## Development

The project uses Gradle and a JDK 17 toolchain.

Run the tests from the project root:

```sh
./gradlew test
```

On Windows PowerShell:

```powershell
.\gradlew.bat test
```

## Reporting issues

When reporting an incorrect suffix, include:

- The input name and any supplied pronunciation.
- The actual output and expected output.
- A pronunciation explanation or reference when the case depends on pronunciation.

Please use fictional examples instead of sharing private user data.
