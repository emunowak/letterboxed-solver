package pl.emunowak.letterboxed.solver.validator.input;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.emunowak.letterboxed.solver.io.reader.WrongInputException;
import pl.emunowak.letterboxed.solver.validator.AbstractValidatorFacade;
import pl.emunowak.letterboxed.solver.validator.ValidatorChain;

import java.util.List;

@Component
@Slf4j
public class InputValidatorFacade extends AbstractValidatorFacade<String, WrongInputException> {
    public InputValidatorFacade( List<ValidatorChain<String, WrongInputException>> validators ) {
        super( validators );
    }
}
