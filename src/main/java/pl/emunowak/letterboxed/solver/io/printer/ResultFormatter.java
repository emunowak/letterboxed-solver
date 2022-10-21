package pl.emunowak.letterboxed.solver.io.printer;

import pl.emunowak.letterboxed.solver.fact.Fact;

import java.util.Collection;

public interface ResultFormatter<T extends Fact> {
    Collection<String> formatResults( Collection<T> resultFacts );
}
