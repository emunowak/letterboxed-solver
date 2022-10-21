package pl.emunowak.letterboxed.solver.fact.creator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.emunowak.letterboxed.solver.config.ConfigurationException;
import pl.emunowak.letterboxed.solver.config.WordsConfiguration;
import pl.emunowak.letterboxed.solver.fact.Fact;
import pl.emunowak.letterboxed.solver.fact.WordFact;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitialFactsCreator implements FactsCreator {

    private final WordsConfiguration wordsConfiguration;

    @Override
    public Collection<Fact> createFacts() {
        try {
            return wordsConfiguration.getWordsStream().map( WordFact::new ).collect( Collectors.toSet() );
        } catch ( ConfigurationException e ) {
            System.out.println( e.getMessage() );
            return List.of();
        }
    }
}
