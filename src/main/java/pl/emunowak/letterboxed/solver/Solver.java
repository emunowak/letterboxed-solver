package pl.emunowak.letterboxed.solver;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;
import pl.emunowak.letterboxed.solver.fact.ResultFact;
import pl.emunowak.letterboxed.solver.fact.creator.FactsCreator;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class Solver {

    private static final String RESULTS_QUERY = "Get results";
    private static final String RESULTS_ITEM = "item";
    private final Collection<FactsCreator> factsCreators;

    public Set<ResultFact> solve( KieBase kieBase ) {
        var kieSession = kieBase.newKieSession();
        addFacts( kieSession );
        kieSession.fireAllRules();
        return getResults( kieSession );
    }

    private void addFacts( KieSession kieSession ) {
        factsCreators.stream()
                .map( FactsCreator::createFacts )
                .flatMap( Collection::stream )
                .forEach( kieSession::insert );
    }

    private Set<ResultFact> getResults( KieSession kieSession ) {
        var results = kieSession.getQueryResults( RESULTS_QUERY );
        return results.toList().stream()
                .map( result -> ( ResultFact ) result.get( RESULTS_ITEM ) )
                .collect( Collectors.toSet() );
    }
}
