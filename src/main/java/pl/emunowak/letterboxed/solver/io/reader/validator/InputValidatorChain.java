package pl.emunowak.letterboxed.solver.io.reader.validator;

import pl.emunowak.letterboxed.solver.io.reader.WrongInputException;

public interface InputValidatorChain {
    String validateInput( String input ) throws WrongInputException;
    void setNext( InputValidatorChain validator );
}
