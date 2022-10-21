package pl.emunowak.letterboxed.solver.io.printer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import pl.emunowak.letterboxed.solver.config.ResourcesConfiguration;

import java.util.Collection;

@Component
@Slf4j
@RequiredArgsConstructor
public class ConsoleResultPrinter implements ResultPrinter {

    private final ResourcesConfiguration textResources;

    @Override
    public void printResult( Collection<String> results ) {
        if ( CollectionUtils.isEmpty( results ) ) {
            printNoResultInfo();
        } else {
            printProperResults( results );
        }
    }

    private void printProperResults( Collection<String> results ) {
        printResultHeader();
        results.forEach( System.out::println );
    }

    private void printNoResultInfo() {
        System.out.println( textResources.getResultsEmptyMessage() );
    }

    private void printResultHeader() {
        System.out.println( textResources.getResultsSpacerMessage() );
        System.out.println( textResources.getResultsHeaderMessage() );
    }
}
