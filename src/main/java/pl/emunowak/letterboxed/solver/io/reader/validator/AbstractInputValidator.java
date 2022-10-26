package pl.emunowak.letterboxed.solver.io.reader.validator;

import lombok.extern.slf4j.Slf4j;
import pl.emunowak.letterboxed.solver.io.reader.WrongInputException;

import java.util.List;

@Slf4j
public abstract class AbstractInputValidator implements InputValidatorChain {

    private InputValidatorChain nextValidator;

    @Override
    public final void setNext( InputValidatorChain validator ) {
        this.nextValidator = validator;
    }

    @Override
    public final String validateInput( String input ) throws WrongInputException {
        var validatedInput = validateAndApplyNext( input );
        return nextValidator.validateInput( validatedInput );
    }

    public static InputValidatorChain buildChain( List<AbstractInputValidator> validators ) {
        if ( validators.isEmpty() ) {
            return new DummyInputValidator();
        }
        for ( int i = 0; i < validators.size(); i++ ) {
            var current = validators.get( i );
            var next = i < validators.size() - 1 ? validators.get( i + 1 ) : new DummyInputValidator();
            current.setNext( next );
        }
        return validators.get( 0 );
    }

    protected abstract String validateAndApplyNext( String input ) throws WrongInputException;
}
