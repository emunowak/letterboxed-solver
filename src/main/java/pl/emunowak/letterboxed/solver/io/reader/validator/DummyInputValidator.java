package pl.emunowak.letterboxed.solver.io.reader.validator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.emunowak.letterboxed.solver.io.reader.WrongInputException;

@Component
@Slf4j
@RequiredArgsConstructor
public class DummyInputValidator implements InputValidator {

    @Override
    public String validateInput( String input ) throws WrongInputException {
        return input;
    }

    @Override
    public void setNext( InputValidator validator ) {
    }
}
