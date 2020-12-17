package com.adventofcode.day04

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PassportTest {

    private val passport = Passport(mapOf(
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
    fun shouldDisplayDataNicely() {
        assertThat(passport.toString())
                .isEqualTo("Passport({BYR ('Birth Year')=1937, IYR ('Issue Year')=2017, EYR ('Expiration Year')=2020, HGT ('Height')=183cm, HCL ('Hair Color')=#fffffd, ECL ('Eye Color')=gry, PID ('Passport ID')=860033327, CID ('Country ID')=147})")
    }
}