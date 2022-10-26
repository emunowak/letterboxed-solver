package pl.emunowak.letterboxed.solver.engine;

import pl.emunowak.letterboxed.solver.engine.solution.SolutionPart;

import java.util.Collection;

public interface Solver {
    void initializeDictionary( Collection<String> words );
    void initializeWallLetters( Collection<String> wallLetters );
    Collection<SolutionPart> solve() throws SolverInvalidConfigurationException;
}
