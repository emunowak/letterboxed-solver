package pl.emunowak.letterboxed.solver.fact.creator;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.emunowak.letterboxed.solver.fact.Fact;
import pl.emunowak.letterboxed.solver.fact.WallLetterFact;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Slf4j
@RequiredArgsConstructor
public class WallLettersFactsCreator implements FactsCreator {

    @Setter
    private List<String> inputStrings;

    @Override
    public Collection<Fact> createFacts() {
        return IntStream.range( 0, inputStrings.size() ).boxed()
                .collect( Collectors.toMap( Function.identity(), i -> toCharCollection( inputStrings.get( i ) ) ) )
                .entrySet().stream()
                .map( entry -> entry.getValue().stream()
                        .map( character -> new WallLetterFact( character, entry.getKey() ) )
                        .collect( Collectors.toList() ) )
                .flatMap( Collection::stream )
                .collect( Collectors.toList() );
    }

    private Collection<Character> toCharCollection( String letters ) {
        return letters.toUpperCase().chars().mapToObj( e -> ( char ) e ).collect( Collectors.toSet() );
    }
}
