package com.necdetzr.duzek
import java.util.Locale
object Duzek {
    private const val VOWELS = "aeıioöuü"
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