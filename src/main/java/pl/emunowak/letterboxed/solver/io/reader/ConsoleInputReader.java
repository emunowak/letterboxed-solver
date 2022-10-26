package pl.emunowak.letterboxed.solver.io.reader;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import pl.emunowak.letterboxed.solver.config.ResourcesConfiguration;
import pl.emunowak.letterboxed.solver.validator.input.InputValidatorFacade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

@Component
@Slf4j
@RequiredArgsConstructor
public class ConsoleInputReader implements InputReader {

    private final InputValidatorFacade validatorFacade;
    private final ResourcesConfiguration textResources;

    public List<String> readInput() {
        var wallLetters = new ArrayList<String>();
        Scanner in = new Scanner( System.in );
        String inputLine;
        int lineNumber = 1;
        do {
            System.out.printf( textResources.getWallLettersInputMessage(), lineNumber );
            inputLine = in.nextLine();
            try {
                validateAndAddLine( wallLetters, inputLine );
                lineNumber++;
            } catch ( WrongInputException e ) {
                System.out.println( e.getMessage() );
            }
        }
        while ( lineNumber <= textResources.getMaxInputWalls() && StringUtils.hasText( inputLine ) );
        return wallLetters;
    }

    private void validateAndAddLine( Collection<String> wallLetters, String line ) throws WrongInputException {
        if ( StringUtils.hasText( line ) ) {
            line = validatorFacade.validate( line );
            wallLetters.add( line );
        }
    }
}
