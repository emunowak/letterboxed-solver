package pl.emunowak.letterboxed.solver.io.reader.validator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.emunowak.letterboxed.solver.config.ResourcesConfiguration;
import pl.emunowak.letterboxed.solver.io.reader.WrongInputException;

@Component
@Slf4j
@RequiredArgsConstructor
public class MaxLengthInputValidator extends AbstractInputValidator {

    private final ResourcesConfiguration textResources;

    @Override
    public String validateAndApplyNext( String input ) throws WrongInputException {
        input = input.replaceAll( "\\s", "" );
        var maxLength = textResources.getValidationLength();
        if ( input.length() > maxLength ) {
            throw new WrongInputException( String.format( textResources.getValidationLengthMessage(), maxLength ) );
        }
        return input;
    }
}
