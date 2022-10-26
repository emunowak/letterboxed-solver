package pl.emunowak.letterboxed.solver.io.reader.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.emunowak.letterboxed.solver.io.reader.WrongInputException;

import java.util.List;

@Component
@Slf4j
public class InputValidatorFacade {
    private final InputValidatorChain headValidator;

    public InputValidatorFacade( List<AbstractInputValidator> validators ) {
        this.headValidator = AbstractInputValidator.buildChain( validators );
    }

    public String validate( String input ) throws WrongInputException {
        return headValidator.validateInput( input );
    }
}
