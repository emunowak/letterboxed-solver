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
public class WallsLengthValidator extends AbstractValidator<LetterWall, SolverInvalidConfigurationException> {

    private final ResourcesConfiguration textResources;

    @Override
    protected LetterWall validateAndApplyNext( LetterWall letterWall ) throws SolverInvalidConfigurationException {
        var maxLength = textResources.getValidationLength();
        if ( !letterWall.getWalls().stream().allMatch( wall -> wall.length() <= maxLength ) ) {
            throw new SolverInvalidConfigurationException( textResources.getWallLettersParseError() );
        }
        return letterWall;
    }
}
