package pl.emunowak.letterboxed.solver.fact.creator;

import pl.emunowak.letterboxed.solver.fact.Fact;

import java.util.Collection;

public interface FactsCreator {
    Collection<Fact> createFacts();
}
