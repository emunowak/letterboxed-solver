package pl.emunowak.letterboxed.solver;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieBase;
import org.springframework.stereotype.Service;
import pl.emunowak.letterboxed.solver.fact.ResultFact;
import pl.emunowak.letterboxed.solver.fact.creator.WallLettersFactsCreator;
import pl.emunowak.letterboxed.solver.io.printer.ResultFactFormatter;
import pl.emunowak.letterboxed.solver.io.printer.ResultPrinter;
import pl.emunowak.letterboxed.solver.io.reader.InputReader;

import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor
public class SolverService {

    private final InputReader inputReader;
    private final RulesLoader rulesLoader;
    private final Solver solver;
    private final ResultFactFormatter resultFormatter;
    private final ResultPrinter resultPrinter;
    private final WallLettersFactsCreator wallLettersFactsCreator;

    public void run() {
        initializeWallLetterFactsCreator();
        var kieBase = initializeSolverBase();
        var resultFacts = runSolver( kieBase );
        printResults( resultFacts );
    }

    private void initializeWallLetterFactsCreator() {
        var wallLetters = inputReader.readInput();
        wallLettersFactsCreator.setInputStrings( wallLetters );
    }

    private KieBase initializeSolverBase() {
        return rulesLoader.initializeSolveBase();
    }

    private Collection<ResultFact> runSolver( KieBase kieBase ) {
        return solver.solve( kieBase );
    }

    private void printResults( Collection<ResultFact> resultFacts ) {
        var results = resultFormatter.formatResults( resultFacts );
        resultPrinter.printResult( results );
    }
}