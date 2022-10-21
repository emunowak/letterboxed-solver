package pl.emunowak.letterboxed.solver.io.reader.validator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.emunowak.letterboxed.solver.config.ResourcesConfiguration;
import pl.emunowak.letterboxed.solver.io.reader.WrongInputException;

@Component
@Slf4j
@RequiredArgsConstructor
public class CharactersInputValidator extends AbstractInputValidator {

    private final ResourcesConfiguration textResources;

    String ONLY_LETTERS_PATTERN = "^[a-zA-Z]+$";

    @Override
    public String validateAndApplyNext( String input ) throws WrongInputException {
        if ( !input.matches( ONLY_LETTERS_PATTERN ) ) {
            throw new WrongInputException( textResources.getValidationLettersMessage() );
        }
        return input.toUpperCase();
    }
}
