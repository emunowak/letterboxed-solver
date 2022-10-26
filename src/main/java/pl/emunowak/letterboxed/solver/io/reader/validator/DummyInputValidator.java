package pl.emunowak.letterboxed.solver.io.reader.validator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class DummyInputValidator implements InputValidatorChain {

    @Override
    public String validateInput( String input ) {
        return input;
    }

    @Override
    public void setNext( InputValidatorChain validator ) {
    }
}
