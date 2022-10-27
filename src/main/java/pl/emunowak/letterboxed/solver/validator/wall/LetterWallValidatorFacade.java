package pl.emunowak.letterboxed.solver.validator.wall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.emunowak.letterboxed.solver.engine.SolverInvalidConfigurationException;
import pl.emunowak.letterboxed.solver.engine.solution.LetterWall;
import pl.emunowak.letterboxed.solver.validator.AbstractValidatorFacade;
import pl.emunowak.letterboxed.solver.validator.ValidatorChain;

import java.util.List;

@Component
@Slf4j
public class LetterWallValidatorFacade extends AbstractValidatorFacade<LetterWall, SolverInvalidConfigurationException> {
    public LetterWallValidatorFacade( List<ValidatorChain<LetterWall, SolverInvalidConfigurationException>> validators ) {
        super( validators );
    }
}
