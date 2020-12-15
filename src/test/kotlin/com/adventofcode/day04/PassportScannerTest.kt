package com.adventofcode.day04

import com.adventofcode.day04.PassportScannerAssert.Companion.assertThatPassportScanner
import com.adventofcode.day04.PassportsValidationAssert.Companion.assertThatPassportValidation
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PassportScannerTest {

    @Test
    fun shouldValidatePassportWhenDataHasAllFieldsCompleted() {
        val input = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"

        assertThatPassportValidation(
                PassportScanner
                        .readPassportFrom(input)
                        .validateAllPassports()
        )
                .onlyContainsValidPassports()
    }

    @Test
    fun shouldValidatePassportWhenDataHasAllFieldsCompletedButCID() {
        val input = "hcl:#ae17e1 iyr:2013\n" +
                "eyr:2024\n" +
                "ecl:brn pid:760753108 byr:1931\n" +
                "hgt:179cm"

        assertThatPassportValidation(
                PassportScanner
                        .readPassportFrom(input)
                        .validateAllPassports()
        )
                .onlyContainsValidPassports()
    }


    @Test
    fun shouldInvalidatePassportWhenSomeDataIsMissing() {
        val input = "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\n" +
                "hcl:#cfa07d byr:1929"
        assertThatPassportValidation(
                PassportScanner
                        .readPassportFrom(input)
                        .validateAllPassports()
        )
                .onlyContainsInvalidPassports()

    }


    private val passport01 = Passport(mapOf(
            Pair(DataField.ECL, "gry"),
            Pair(DataField.PID, "860033327"),
            Pair(DataField.EYR, "2020"),
            Pair(DataField.HCL, "#fffffd"),
            Pair(DataField.BYR, "1937"),
            Pair(DataField.IYR, "2017"),
            Pair(DataField.CID, "147"),
            Pair(DataField.HGT, "183cm")
    ))

    @Test
    fun shouldReadPassportFromSimpleRawData() {
        val input = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"

        assertThatPassportScanner(PassportScanner.readPassportFrom(input))
                .hasReadThisPassport(passport01)

    }


    @Test
    fun shouldReadPassportFromRawDataWithOneBreakLine() {
        val input = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n" +
                "byr:1937 iyr:2017 cid:147 hgt:183cm"

        assertThatPassportScanner(PassportScanner.readPassportFrom(input))
                .hasReadThisPassport(passport01)

    }


    @Test
    fun shouldReadMultiplePassortsFromRawData() {
        val input = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n" +
                "byr:1937 iyr:2017 cid:147 hgt:183cm\n" +
                "\n" +
                "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\n" +
                "hcl:#cfa07d byr:1929"

        val expectedPassports = listOf<Passport>(
                passport01
                ,
                Passport(mapOf(
                        Pair(DataField.IYR, "2013"),
                        Pair(DataField.ECL, "amb"),
                        Pair(DataField.CID, "350"),
                        Pair(DataField.EYR, "2023"),
                        Pair(DataField.PID, "028048884"),
                        Pair(DataField.HCL, "#cfa07d"),
                        Pair(DataField.BYR, "1929")
                )
                ))

        assertThat(PassportScanner.readPassportsFrom(input))
                .hasFieldOrPropertyWithValue("passports", expectedPassports)

    }


}