package com.necdetzr.duzek
import java.util.Locale
/** Formats Turkish dative forms of bare proper names. */
object Duzek {
    private const val VOWELS = "aeıioöuü"
    /**
     * Returns the trimmed [name] with an apostrophe and its dative suffix.
     *
     * Suffix selection uses Turkish vowel harmony and adds `y` after a vowel.
     * When [pronunciation] is supplied, both decisions use its Turkish phonetic
     * spelling while the output preserves the name's original spelling and casing.
     * Pronunciation-dependent exceptions are not inferred automatically.
     *
     * @throws IllegalArgumentException if the name is blank, an explicitly supplied
     * pronunciation is blank, or the spoken form contains no Turkish vowel.
     */
    fun dative(
        name:String,
        pronunciation: String? = null,
    ) : String{
        val trimmedName = name.trim()
        require(trimmedName.isNotBlank()) {"İsim boş olamaz"}

        val spokenForm = pronunciation?.trim() ?: trimmedName
        require(spokenForm.isNotEmpty()) { "Okunuş boş olamaz." }

        val normalizedPronunciation =
            spokenForm.lowercase(Locale.forLanguageTag("tr"))


        val lastVowel = normalizedPronunciation.lastOrNull {
            it in VOWELS
        }

        requireNotNull(lastVowel) {
            "İsim veya belirtilen okunuş Türkçe bir ünlü içermeli."
        }

        val suffix = if(lastVowel in "aıou") "a" else "e"
        val connector = if(normalizedPronunciation.last() in VOWELS) "y" else ""
        return "$trimmedName'$connector$suffix"
    }
}
