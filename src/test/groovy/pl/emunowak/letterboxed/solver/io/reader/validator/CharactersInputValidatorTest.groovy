package pl.emunowak.letterboxed.solver.io.reader.validator

import pl.emunowak.letterboxed.solver.config.ResourcesConfiguration
import pl.emunowak.letterboxed.solver.io.reader.WrongInputException
import spock.lang.Specification

class CharactersInputValidatorTest extends Specification {

    ResourcesConfiguration resourcesConfiguration = Mock()
    InputValidator validator = new CharactersInputValidator( resourcesConfiguration )

    private static MOCK_VALIDATION_MESSAGE = "Mock validation message"

    def "should allow characters only input"() {
        when:
        validator.validateAndApplyNext( inputString )

        then:
        noExceptionThrown()

        where:
        lp | inputString
        1  | "a"
        2  | "abcdefghi"
        3  | "B"
        4  | "XYZQ"
        5  | "aCeGiJl"
    }

    def "should not allow non-characters input"() {
        given:
        resourcesConfiguration.getValidationLettersMessage() >> MOCK_VALIDATION_MESSAGE

        when:
        validator.validateAndApplyNext( inputString )

        then:
        def exception = thrown( WrongInputException )
        exception.message == MOCK_VALIDATION_MESSAGE

        where:
        lp | inputString
        1  | "1"
        2  | "123456"
        3  | "asd4"
        4  | "09LXC"
        5  | "!qopenc"
        6  | "abcd@#%"
        7  | "  "
        8  | "_v "
        9  | "  ad 34 sd"
    }

    def "should return upper letters characters"() {
        when:
        def outputString = validator.validateAndApplyNext( inputString )

        then:
        outputString == expextedOutputString

        where:
        inputString || expextedOutputString
        "a"         || "A"
        "abcdefghi" || "ABCDEFGHI"
        "B"         || "B"
        "XYZQ"      || "XYZQ"
        "aCeGiJl"   || "ACEGIJL"
    }
}
