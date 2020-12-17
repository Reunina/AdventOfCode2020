package com.adventofcode.day04

import com.adventofcode.day04.DataFieldAssert.Companion.assertThatDataField
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class DataFieldTest {

    @Test
    fun shouldValidateDataFieldByrAccordingToItsRules() {

        val byr = DataField.BYR

        assertThatDataField(byr).invalidates("")
                .`as`("$byr must be a digit.")

        assertAll(
                "$byr must be four digits; at least 1920 and at most 2002.",
                {
                    assertThatDataField(byr).invalidates("1")
                    assertThatDataField(byr).invalidates("12")
                    assertThatDataField(byr).invalidates("123")
                    assertThatDataField(byr).invalidates("1919")
                    assertThatDataField(byr).validates("1920")
                    assertThatDataField(byr).validates("1921")
                    assertThatDataField(byr).validates("2002")
                    assertThatDataField(byr).invalidates("2003")

                }
        )
    }

    @Test
    fun shouldValidateDataFieldIyrAccordingToItsRules() {

        val iyr = DataField.IYR

        assertThatDataField(iyr).invalidates("")
                .`as`("$iyr must be a digit.")

        assertAll(
                "$iyr must be four digits; at least 2010 and at most 2020.",
                {
                    assertThatDataField(iyr).invalidates("1")
                    assertThatDataField(iyr).invalidates("12")
                    assertThatDataField(iyr).invalidates("123")
                    assertThatDataField(iyr).invalidates("1234")
                    assertThatDataField(iyr).invalidates("2009")
                    assertThatDataField(iyr).validates("2010")
                    assertThatDataField(iyr).validates("2020")
                    assertThatDataField(iyr).invalidates("2021")

                }
        )
    }


    @Test
    fun shouldValidateDataFieldEyrAccordingToItsRules() {

        val eyr = DataField.EYR
        assertThatDataField(eyr).invalidates("")
                .`as`("$eyr must be a digit.")

        assertAll(
                "$eyr must be four digits; at least 2020 and at most 2030.",
                {
                    assertThatDataField(eyr).invalidates("1")
                    assertThatDataField(eyr).invalidates("12")
                    assertThatDataField(eyr).invalidates("123")
                    assertThatDataField(eyr).invalidates("1234")
                    assertThatDataField(eyr).invalidates("2019")
                    assertThatDataField(eyr).validates("2020")
                    assertThatDataField(eyr).validates("2030")
                    assertThatDataField(eyr).invalidates("2031")

                }
        )
    }

    @Test
    fun shouldValidateDataFieldHgtAccordingToItsRules() {

        val hgt = DataField.HGT
        assertAll(
                "$hgt must be a number followed by either 'cm' or 'in'",
                {
                    assertThatDataField(hgt).invalidates("cm")
                    assertThatDataField(hgt).invalidates("in")
                    assertThatDataField(hgt).invalidates("123")

                }
        )
        assertAll(
                "$hgt must be a number and if ends with 'cm', must be at least 150 and at most 193.",
                {
                    assertThatDataField(hgt).invalidates("149cm")
                    assertThatDataField(hgt).validates("150cm")
                    assertThatDataField(hgt).validates("193cm")
                    assertThatDataField(hgt).invalidates("194cm")

                }
        )

        assertAll(
                "$hgt must be a number and if ends with 'cm', must be at least 59 and at most 76.",
                {
                    assertThatDataField(hgt).invalidates("58in")
                    assertThatDataField(hgt).validates("59in")
                    assertThatDataField(hgt).validates("76in")
                    assertThatDataField(hgt).invalidates("77in")
                }
        )

    }

    @Test
    fun shouldValidateDataFieldHclAccordingToItsRules() {

        val hcl = DataField.HCL
        assertAll(
                "$hcl must starts with '#' and be followed by exactly six characters 0-9 or a-f.",
                {
                    assertThatDataField(hcl).invalidates("a")
                    assertThatDataField(hcl).invalidates("#00000")
                    assertThatDataField(hcl).invalidates("#00000aa")
                    assertThatDataField(hcl).invalidates("0#0000aa")
                    assertThatDataField(hcl).invalidates("#abcdef9")
                    assertThatDataField(hcl).validates("#abcdef")
                    assertThatDataField(hcl).validates("#999999")
                    assertThatDataField(hcl).validates("#abcde9")
                }
        )


    }

    @Test
    fun shouldValidateDataFieldEclAccordingToItsRules() {

        val ecl = DataField.ECL

        assertAll(
                "${DataField.ECL} must be exactly one of: amb blu brn gry grn hzl oth",
                {
                    assertThatDataField(ecl).invalidates("")
                    assertThatDataField(ecl).invalidates("0")
                    assertThatDataField(ecl).invalidates("ambb")
                    listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").forEach {
                        assertThatDataField(ecl).validates(it)
                    }
                }
        )
    }


    @Test
    fun shouldValidateDataFieldPidAccordingToItsRules() {

        val pid = DataField.PID

        assertAll(
                "${DataField.PID} must be a nine-digit number, including leading zeroes.",
                {
                    assertThatDataField(pid).invalidates("")
                    assertThatDataField(pid).invalidates("0")
                    assertThatDataField(pid).invalidates("ambb")
                    assertThatDataField(pid).invalidates("0123456789")
                    assertThatDataField(pid).validates("999999999")
                    assertThatDataField(pid).validates("000000000")
                    assertThatDataField(pid).validates("000000001")
                }
        )


    }

}
