package com.necdetzr.duzek

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class DuzekTest {
    @Test
    fun `unsuzle biten adlara uygun ek eklenir`(){
        assertEquals("Murat'a", Duzek.dative("Murat"))
        assertEquals("Mehmet'e", Duzek.dative("Mehmet"))
    }

    @Test
    fun `unluyle biten adlara kaynastirma harfi eklenir`(){
        assertEquals("Ece'ye", Duzek.dative("Ece"))
        assertEquals("Arda'ya", Duzek.dative("Arda"))
    }
    @Test
    fun `selects the correct suffix for all eight vowels`() {
        val examples = mapOf(
            "Murat" to "Murat'a",
            "Yıldız" to "Yıldız'a",
            "Oğuz" to "Oğuz'a",
            "Onur" to "Onur'a",
            "Mehmet" to "Mehmet'e",
            "Selin" to "Selin'e",
            "Gök" to "Gök'e",
            "Öykü" to "Öykü'ye",
            "Gökçe" to "Gökçe'ye"
        )

        examples.forEach { (name, expected) ->
            assertEquals(expected, Duzek.dative(name), name)
        }
    }

    @Test
    fun `preserves the spelling of uppercase names`() {
        assertEquals("MURAT'a", Duzek.dative("MURAT"))
        assertEquals("ECE'ye", Duzek.dative("ECE"))
        assertEquals("IŞIL'a", Duzek.dative("IŞIL"))
        assertEquals("İDİL'e", Duzek.dative("İDİL"))
    }
    @Test
    fun `rejects empty or blank names`() {
        listOf("", " ", "\t", "\n").forEach { name ->
            assertFailsWith<IllegalArgumentException> {
                Duzek.dative(name)
            }
        }
    }

    @Test
    fun `rejects names without a Turkish vowel`() {
        listOf("TRT", "123", "🙂").forEach { name ->
            assertFailsWith<IllegalArgumentException> {
                Duzek.dative(name)
            }
        }
    }
    @Test
    fun `trims leading and trailing whitespace`() {
        assertEquals("Murat'a", Duzek.dative(" Murat "))
        assertEquals("Ece'ye", Duzek.dative("\tEce\n"))
        assertEquals("Ali Can'a", Duzek.dative(" Ali Can "))
    }
    @Test
    fun `adds y after vowel endings`() {
        val examples = mapOf(
            "Arda" to "Arda'ya",
            "Ece" to "Ece'ye",
            "Aslı" to "Aslı'ya",
            "Ali" to "Ali'ye",
            "Banu" to "Banu'ya",
            "Öykü" to "Öykü'ye"
        )

        examples.forEach { (name, expected) ->
            assertEquals(expected, Duzek.dative(name), name)
        }
    }
}