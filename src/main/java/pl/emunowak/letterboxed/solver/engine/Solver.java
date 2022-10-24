package pl.emunowak.letterboxed.solver.engine;

import pl.emunowak.letterboxed.solver.engine.solution.SolutionPart;

import java.util.Collection;

public interface Solver {
    void initializeDictionary( Collection<String> words ) throws SolverInvalidConfigurationException;
    void initializeWallLetters( Collection<String> wallLetters ) throws SolverInvalidConfigurationException;
    Collection<SolutionPart> solve() throws SolverNotInitializedException;
}
