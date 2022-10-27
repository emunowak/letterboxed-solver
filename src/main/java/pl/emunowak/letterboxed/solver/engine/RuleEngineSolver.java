package pl.emunowak.letterboxed.solver.engine;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.emunowak.letterboxed.solver.engine.solution.LetterWall;
import pl.emunowak.letterboxed.solver.engine.solution.SolutionPart;
import pl.emunowak.letterboxed.solver.validator.wall.LetterWallValidatorFacade;

import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor
public class RuleEngineSolver implements Solver {

    private final LetterWallValidatorFacade validatorFacade;

    private LetterWall letterWall = new LetterWall();

    @Override
    public void initializeDictionary( Collection<String> words ) {
        this.letterWall.setWords( words );
    }

    @Override
    public void initializeWallLetters( Collection<String> wallLetters ) {
        this.letterWall.setWalls( wallLetters );
    }

    @Override
    public Collection<SolutionPart> solve() throws SolverInvalidConfigurationException {
        validatorFacade.validate( letterWall );
        return null;
    }

}
