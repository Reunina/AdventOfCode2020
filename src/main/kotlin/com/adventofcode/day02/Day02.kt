import com.adventofcode.day02.Passwords
import com.adventofcode.day02.SledRentalPolicy
import com.adventofcode.day02.TobogganCorporatePolicy
import java.io.File

fun main(args: Array<String>) {
    val input: List<String> =
            File("src/main/resources/com.adventofcode.day02/passwords_and_associated_corporate_policy.txt")
                    .readLines()

    val allValidPasswordsForSledRentalPolicy =
            Passwords(input)
                    .findAllValidForPolicy { policyAsString: String -> SledRentalPolicy.fromInput(policyAsString) }

    println(
            "Day 02, with Sled Rental policy, found : " + allValidPasswordsForSledRentalPolicy.size
                    + " valid passwords over " + input.size + " passwords."
    )
    val allValidPasswordsForTobogganCorporatePolicy =
            Passwords(input)
                    .findAllValidForPolicy { policyAsString: String -> TobogganCorporatePolicy.fromInput(policyAsString) }

    println(
            "Day 02, with Toboggan Corporate policy, found : " + allValidPasswordsForTobogganCorporatePolicy.size
                    + " valid passwords over " + input.size + " passwords."
    )

    println("see details below:\n"
            +"__ with Sled Rental Policy__ \n"
            + allValidPasswordsForSledRentalPolicy.joinToString("\n")
            +"__ with Toboggan Corpporate Policy__ \n"
            + allValidPasswordsForTobogganCorporatePolicy.joinToString("\n")
    )
}
