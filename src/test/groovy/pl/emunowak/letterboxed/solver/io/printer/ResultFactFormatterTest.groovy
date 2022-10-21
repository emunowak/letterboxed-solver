package pl.emunowak.letterboxed.solver.io.printer

import pl.emunowak.letterboxed.solver.config.ResourcesConfiguration
import pl.emunowak.letterboxed.solver.fact.ResultFact
import spock.lang.Specification

class ResultFactFormatterTest extends Specification {

    ResourcesConfiguration resourcesConfiguration = Mock()
    ResultFormatter<ResultFact> formatter = new ResultFactFormatter( resourcesConfiguration )

    private static JOIN_DELIMITER = "-"

    def "should return joined words"() {
        given:
        resourcesConfiguration.getResultWordsDelimiter() >> JOIN_DELIMITER

        when:
        def joinedWords = formatter.formatResult( fact )

        then:
        joinedWords == expectedJoinedWords

        where:
        fact                                      || expectedJoinedWords
        new ResultFact( [] )                      || ""
        new ResultFact( ["a"] )                   || "a"
        new ResultFact( ["abc", "def"] )          || "abc" + JOIN_DELIMITER + "def"
        new ResultFact( ["abc", "def", " 123 "] ) || "abc" + JOIN_DELIMITER + "def" + JOIN_DELIMITER + " 123 "
    }

}
