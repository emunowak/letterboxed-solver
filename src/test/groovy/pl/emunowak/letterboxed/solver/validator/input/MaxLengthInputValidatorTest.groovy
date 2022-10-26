package pl.emunowak.letterboxed.solver.validator.input

import pl.emunowak.letterboxed.solver.config.ResourcesConfiguration
import pl.emunowak.letterboxed.solver.io.reader.WrongInputException
import pl.emunowak.letterboxed.solver.validator.ValidatorChain
import pl.emunowak.letterboxed.solver.validator.input.MaxLengthInputValidator
import spock.lang.Specification

class MaxLengthInputValidatorTest extends Specification {

    ResourcesConfiguration resourcesConfiguration = Mock()
    ValidatorChain<String, WrongInputException> validator = new MaxLengthInputValidator( resourcesConfiguration )

    private static MOCK_VALIDATION_MESSAGE = "Mock validation message"
    private static MOCK_MAX_LENGTH = 5

    def "should allow input of correct length excluding white spaces"() {
        given:
        resourcesConfiguration.getValidationLength() >> MOCK_MAX_LENGTH

        when:
        validator.validateAndApplyNext( inputString )

        then:
        noExceptionThrown()

        where:
        lp | inputString
        1  | "a"
        2  | "abcde"
        3  | " zxvqy "
        4  | "   s  d  r    f     l     "
        5  | "1234 5    "
        6  | "      "
    }

    def "should not allow too long input excluding white spaces"() {
        given:
        resourcesConfiguration.getValidationLengthMessage() >> MOCK_VALIDATION_MESSAGE
        resourcesConfiguration.getValidationLength() >> MOCK_MAX_LENGTH

        when:
        validator.validateAndApplyNext( inputString )

        then:
        def exception = thrown( WrongInputException )
        exception.message == MOCK_VALIDATION_MESSAGE

        where:
        lp | inputString
        1  | "abcdef"
        2  | "  abcdefgqwewerwrdfsdf "
        3  | "   s  d  r    f     l   x  "
        4  | "1234 5    0987"
        5  | "      _+{}:."
    }

    def "should return output without white spaces inside"() {
        given:
        resourcesConfiguration.getValidationLength() >> MOCK_MAX_LENGTH

        when:
        def outputString = validator.validateAndApplyNext( inputString )

        then:
        outputString == expectedOutputString

        where:
        inputString                  || expectedOutputString
        "a"                          || "a"
        "abcde"                      || "abcde"
        " zxvqy "                    || "zxvqy"
        "   s  d  r    f     l     " || "sdrfl"
        "1234 5    "                 || "12345"
        "      "                     || ""
    }
}
