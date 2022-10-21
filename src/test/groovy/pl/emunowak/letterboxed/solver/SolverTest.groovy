package pl.emunowak.letterboxed.solver

import com.google.common.collect.Sets
import pl.emunowak.letterboxed.solver.fact.Fact
import pl.emunowak.letterboxed.solver.fact.WallLetterFact
import pl.emunowak.letterboxed.solver.fact.WordFact
import pl.emunowak.letterboxed.solver.fact.creator.FactsCreator
import spock.lang.Specification

import java.nio.charset.StandardCharsets
import java.util.stream.Collectors
import java.util.stream.IntStream

class SolverTest extends Specification {

    private static String rulesFileName = "/rules/solver.drl"
    private static String rulesContent

    FactsCreator factsCreator = Mock()
    Solver solver = new Solver( List.of( factsCreator ) )

    void setupSpec() {
        def inputStream = this.getClass().getResourceAsStream( rulesFileName )
        rulesContent = new String( inputStream.readAllBytes(), StandardCharsets.UTF_8 )
    }

    def "should solve puzzle"() {
        given:
        def kieBase = RulesTestUtils.getKieBaseWithPackages( rulesContent )
        factsCreator.createFacts() >> {
            def facts = new ArrayList<Fact>()
            facts.addAll( getWallLetterFacts( wallLetters as List<Character[]> ) )
            facts.addAll( getWordFacts() )
            facts
        }

        when:
        def results = solver.solve( kieBase )

        then:
        results.size() == resultWords.size()
        Sets.difference(
                results.stream().map( result -> result.words ).collect( Collectors.toSet() ),
                resultWords
        ).size() == 0

        where:
        wallLetters                          || resultWords
        [['S', 'L'], ['O', 'E'], ['V', 'R']] || [["SOLVER"]] as Set
        [['W', 'R', 'S'], ['O', 'D']]        || [["WORDS"]] as Set
        [['F', 'R', 'D'], ['A', 'I', 'O']]   || [["FAR", "RID", "DO"]] as Set
        [['F', 'R'], ['A'], ['E']]           || [["FEAR"], ["FARE"]] as Set
    }

    def getWallLetterFacts( List<Character[]> wallLetters ) {
        IntStream.range( 0, wallLetters.size() )
                .mapToObj( index -> getWallLetterFacts( index, wallLetters[index] as Character[] ) )
                .collect( Collectors.toSet() )
                .flatten() as Collection<? extends Fact>
    }

    def getWallLetterFacts( int wallIndex, Character[] letters ) {
        IntStream.range( 0, letters.length )
                .mapToObj( index -> new WallLetterFact( letters[index], wallIndex ) )
                .collect( Collectors.toSet() )
    }

    def getWordFacts() {
        getSampleWords().stream().map( WordFact::new ).collect( Collectors.toSet() )
    }

    def getSampleWords() {
        [
                "TEST",
                "WORD",
                "WORDS",
                "SOLVER",
                "SILVER",
                "SOLVE",
                "DO",
                "FAR",
                "RID",
                "FEAR",
                "FARE"
        ]
    }
}
