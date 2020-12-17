package com.adventofcode.day04

import com.adventofcode.day04.PassportScannerAssert.Companion.assertThatPassportScanner
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PassportScannerTest {



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
    private val passport02 = Passport(mapOf(
            Pair(DataField.IYR, "2013"),
            Pair(DataField.ECL, "amb"),
            Pair(DataField.CID, "350"),
            Pair(DataField.EYR, "2023"),
            Pair(DataField.PID, "028048884"),
            Pair(DataField.HCL, "#cfa07d"),
            Pair(DataField.BYR, "1929")
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
                "byr:1937 iyr:2017 cid:147 hgt:183cm" +
                "\n" +
                "\n" +
                "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\n" +
                "hcl:#cfa07d byr:1929"

        val expectedPassports = listOf(passport01, passport02)


        assertThatPassportScanner(PassportScanner.readPassportsFrom(input))
                .hasReadThesePassports(expectedPassports)

    }




}