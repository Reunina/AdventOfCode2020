package com.adventofcode.day04

import org.junit.jupiter.api.Test

class PassportScannerWithSimpleValidationTest {

    @Test
    fun shouldValidatePassportWhenDataHasAllFieldsCompleted() {
        val input = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"

        PassportsValidatorAssert.assertThatPassportValidator(
                PassportScanner
                        .readPassportFrom(input)
                        .simplyValidateAllPassports()
        )
                .onlyContainsValidPassports()
    }

    @Test
    fun shouldValidatePassportWhenDataHasAllFieldsCompletedButCID() {
        val input = "hcl:#ae17e1 iyr:2013\n" +
                "eyr:2024\n" +
                "ecl:brn pid:760753108 byr:1931\n" +
                "hgt:179cm"

        PassportsValidatorAssert.assertThatPassportValidator(
                PassportScanner
                        .readPassportFrom(input)
                        .simplyValidateAllPassports()
        )
                .onlyContainsValidPassports()
    }


    @Test
    fun shouldInvalidatePassportWhenSomeDataIsMissing() {
        val input = "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\n" +
                "hcl:#cfa07d byr:1929"
        PassportsValidatorAssert.assertThatPassportValidator(
                PassportScanner
                        .readPassportFrom(input)
                        .simplyValidateAllPassports()
        )
                .onlyContainsInvalidPassports()

    }


    @Test
    fun shouldDisplayNumberOfValidData() {
        val passport01 = "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\n" +
                "hcl:#cfa07d byr:1929"
        val passport02 = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm\""
        val input = "$passport01\n\n$passport02"

        PassportsValidatorAssert.assertThatPassportValidator(
                PassportScanner
                        .readPassportsFrom(input)
                        .simplyValidateAllPassports()
        )
                .onlyContainsInvalidPassports()
                .displaysNumberOfValidatedDataFoundAs("SimplePassportsValidator: 1 data(s) validated among:")
    }

    @Test
    fun shouldGiveInvalidityCauseWhenPassportIsInvalid() {

        PassportsValidatorAssert.assertThatPassportValidator(
                PassportScanner
                        .readPassportFrom("iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884")
                        .simplyValidateAllPassports()
        )
                .displayInvalidityCauseAs("Missing some data = [BYR ('Birth Year'), HGT ('Height'), HCL ('Hair Color')]")
    }

}

