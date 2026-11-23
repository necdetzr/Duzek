package com.necdetzr.duzek
import java.util.Locale
object Duzek {
    private const val VOWELS = "aeıioöuü"
    fun dative(name:String) : String{
        val trimmedName = name.trim()
        require(trimmedName.isNotBlank()) {"İsim boş olamaz"}
        val normalizedName = trimmedName.lowercase(Locale.forLanguageTag("tr"))

        val lastVowel = normalizedName.lastOrNull {
            it in VOWELS
        }

        requireNotNull(lastVowel){
            "İsim Türkçe ünlü bir harf içermeli"
        }

        val suffix = if(lastVowel in "aıou") "a" else "e"
        val connector = if(normalizedName.last().lowercaseChar() in VOWELS) "y" else ""
        return "$trimmedName'$connector$suffix"
    }
}