package pl.emunowak.letterboxed.solver.validator.wall;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.emunowak.letterboxed.solver.config.ResourcesConfiguration;
import pl.emunowak.letterboxed.solver.engine.SolverInvalidConfigurationException;
import pl.emunowak.letterboxed.solver.engine.solution.LetterWall;
import pl.emunowak.letterboxed.solver.validator.AbstractValidator;

@Component
@Slf4j
@RequiredArgsConstructor
public class CharactersWallsValidator extends AbstractValidator<LetterWall, SolverInvalidConfigurationException> {

    private final ResourcesConfiguration textResources;

    private static final String ONLY_LETTERS_PATTERN = "^[a-zA-Z]+$";

    @Override
    protected LetterWall validateAndApplyNext( LetterWall letterWall ) throws SolverInvalidConfigurationException {
        if ( !letterWall.getWalls().stream().allMatch( word -> word.matches( ONLY_LETTERS_PATTERN ) ) ) {
            throw new SolverInvalidConfigurationException( textResources.getWordsParseError() );
        }
        return letterWall;
    }
}
