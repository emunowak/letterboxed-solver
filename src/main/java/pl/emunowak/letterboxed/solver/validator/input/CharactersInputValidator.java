package pl.emunowak.letterboxed.solver.validator.input;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.emunowak.letterboxed.solver.config.ResourcesConfiguration;
import pl.emunowak.letterboxed.solver.io.reader.WrongInputException;
import pl.emunowak.letterboxed.solver.validator.AbstractValidator;

@Component
@Slf4j
@RequiredArgsConstructor
public class CharactersInputValidator extends AbstractValidator<String, WrongInputException> {

    private final ResourcesConfiguration textResources;

    private static final String ONLY_LETTERS_PATTERN = "^[a-zA-Z]+$";

    @Override
    protected String validateAndApplyNext( String input ) throws WrongInputException {
        if ( !input.matches( ONLY_LETTERS_PATTERN ) ) {
            throw new WrongInputException( textResources.getValidationLettersMessage() );
        }
        return input.toUpperCase();
    }
}
