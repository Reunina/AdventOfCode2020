package com.adventofcode.day04

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PassportScannerWithFullValidationTest {

    @Test
    fun shouldValidatePassportWhenAllFieldsAreCompletedWithValidData() {

        assertAll(
                {
                    PassportsValidatorAssert.assertThatPassportValidator(
                            PassportScanner
                                    .readPassportFrom("eyr:2029 ecl:blu cid:129 byr:1989\n" +
                                            "iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm")
                                    .fullyValidateAllPassports()
                    )
                            .onlyContainsValidPassports()
                },
                {
                    PassportsValidatorAssert.assertThatPassportValidator(
                            PassportScanner
                                    .readPassportFrom("hcl:#888785\n" +
                                            "hgt:164cm byr:2001 iyr:2015 cid:88\n" +
                                            "pid:545766238 ecl:hzl\n" +
                                            "eyr:2022")
                                    .fullyValidateAllPassports()
                    )
                            .onlyContainsValidPassports()
                }
        )


    }

    @Test
    fun shouldValidatePassportWhenAllFieldsButCIDAreCompletedWithValidData() {

        assertAll(
                {
                    PassportsValidatorAssert.assertThatPassportValidator(
                            PassportScanner
                                    .readPassportFrom("pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980\n" +
                                            "hcl:#623a2f")
                                    .fullyValidateAllPassports()
                    )
                            .onlyContainsValidPassports()
                },
                {
                    PassportsValidatorAssert.assertThatPassportValidator(
                            PassportScanner
                                    .readPassportFrom("iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719")
                                    .fullyValidateAllPassports()
                    )
                            .onlyContainsValidPassports()
                }
        )
    }


    @Test
    fun shouldInvalidatePassportWhenSomeDataIsMissing() {
        assertAll(
                {
                    PassportsValidatorAssert.assertThatPassportValidator(
                            PassportScanner
                                    .readPassportFrom(
                                            "eyr:1972 cid:100\n" +
                                                    "hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926")
                                    .fullyValidateAllPassports()
                    )
                            .onlyContainsInvalidPassports()
                },
                {
                    PassportsValidatorAssert.assertThatPassportValidator(
                            PassportScanner
                                    .readPassportFrom("hcl:dab227 iyr:2012\n" +
                                            "ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277")
                                    .fullyValidateAllPassports()
                    )
                            .onlyContainsInvalidPassports()
                },
                {
                    PassportsValidatorAssert.assertThatPassportValidator(
                            PassportScanner
                                    .readPassportFrom("iyr:2019\n" +
                                            "hcl:#602927 eyr:1967 hgt:170cm\n" +
                                            "ecl:grn pid:012533040 byr:1946")
                                    .fullyValidateAllPassports()
                    )
                            .onlyContainsInvalidPassports()
                },
                {
                    PassportsValidatorAssert.assertThatPassportValidator(
                            PassportScanner
                                    .readPassportFrom("hgt:59cm ecl:zzz\n" +
                                            "eyr:2038 hcl:74454a iyr:2023\n" +
                                            "pid:3556412378 byr:2007")
                                    .fullyValidateAllPassports()
                    )
                            .onlyContainsInvalidPassports()
                }
        )

    }


    @Test
    fun shouldDisplayNumberOfValidData() {
        val passport01 = "pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980\n" +
                "hcl:#623a2f"
        val passport02 = "ecl:gry"
        val input = "$passport01\n\n$passport02"

        PassportsValidatorAssert.assertThatPassportValidator(
                PassportScanner
                        .readPassportsFrom(input)
                        .fullyValidateAllPassports()
        )
                .onlyContainsInvalidPassports()
                .displaysNumberOfValidatedDataFoundAs("FullPassportsValidator: 2 data(s) validated among:")
    }

    @Test
    fun shouldGiveInvalidityCauseWhenPassportIsInvalid() {
        assertAll(
                "Invalidity cause should be given when invalidating passport",
                {
                    assertInvalidityCause(
                            "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884",
                            "Missing some data = [BYR ('Birth Year'), HGT ('Height'), HCL ('Hair Color')]")
                },
                {
                    assertInvalidityCause(
                            "ecl:gry pid:860033327 eyr:2020 hcl:#fgfffd byr:1937 iyr:2017 cid:147 hgt:1383cm",
                            "Found invalid data: {HCL ('Hair Color')=#fgfffd, HGT ('Height')=1383cm}")
                }
        )
    }

    private fun assertInvalidityCause(input: String, cause: String) {
        PassportsValidatorAssert.assertThatPassportValidator(
                PassportScanner
                        .readPassportFrom(input)
                        .fullyValidateAllPassports()
        )
                .displayInvalidityCauseAs(cause)
    }

}










