package pl.emunowak.letterboxed;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.emunowak.letterboxed.solver.SolverService;

@SpringBootApplication
@Slf4j
public class LetterboxedSolver {

    public static void main( String[] args ) {
        var context = SpringApplication.run( LetterboxedSolver.class, args );
        context.getBean( SolverService.class ).run();
    }
}
