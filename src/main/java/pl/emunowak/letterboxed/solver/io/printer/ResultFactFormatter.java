package pl.emunowak.letterboxed.solver.io.printer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.emunowak.letterboxed.solver.config.ResourcesConfiguration;
import pl.emunowak.letterboxed.solver.fact.ResultFact;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class ResultFactFormatter implements ResultFormatter<ResultFact> {

    private final ResourcesConfiguration textResources;

    public Collection<String> formatResults( Collection<ResultFact> resultFacts ) {
        return resultFacts.stream()
                .map( this::formatResult )
                .collect( Collectors.toList() );
    }

    String formatResult( ResultFact fact ) {
        return String.join( textResources.getResultWordsDelimiter(), fact.getWords() );
    }
}
