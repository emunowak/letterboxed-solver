package pl.emunowak.letterboxed.solver.engine;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.server.Cookie;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pl.emunowak.letterboxed.solver.config.ResourcesConfiguration;
import pl.emunowak.letterboxed.solver.engine.solution.LetterWall;
import pl.emunowak.letterboxed.solver.engine.solution.SolutionPart;

import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor
public class RuleEngineSolver implements Solver {

    private final ResourcesConfiguration textResources;
    private final LetterWall letterWall = new LetterWall();

    private static final String ONLY_LETTERS_PATTERN = "^[a-zA-Z]+$";

    @Override
    public void initializeDictionary( Collection<String> words ) {
        this.letterWall.setWords( words );
    }

    @Override
    public void initializeWallLetters( Collection<String> wallLetters ) {
        this.letterWall.setLetters( wallLetters );
    }

    @Override
    public Collection<SolutionPart> solve() throws SolverInvalidConfigurationException {
        if( CollectionUtils.isEmpty( letterWall.getWords() ) || CollectionUtils.isEmpty( letterWall.getLetters() ) ) {
            throw new SolverInvalidConfigurationException( textResources.getSolverInitializationError() );
        }
        return null;
    }

    private void validateDictionaryWords( Collection<String> words ) throws SolverInvalidConfigurationException {
        if ( !words.stream().allMatch( word -> word.matches( ONLY_LETTERS_PATTERN ) ) ) {
            throw new SolverInvalidConfigurationException( textResources.getWordsParseError() );
        }
    }

    private void validateWallLetters( Collection<String> wallLetters ) throws SolverInvalidConfigurationException {
        validateWallLength( wallLetters );
        validateWallCharacters( wallLetters );
    }

    private void validateWallLength( Collection<String> wallLetters ) throws SolverInvalidConfigurationException {
        var maxLength = textResources.getValidationLength();
        if ( !wallLetters.stream().allMatch( wall -> wall.length() <= maxLength ) ) {
            throw new SolverInvalidConfigurationException( textResources.getWallLettersParseError() );
        }
    }

    private void validateWallCharacters( Collection<String> wallLetters ) throws SolverInvalidConfigurationException {
        if ( !wallLetters.stream().allMatch( wall -> wall.matches( ONLY_LETTERS_PATTERN ) ) ) {
            throw new SolverInvalidConfigurationException( textResources.getWallLengthParseError() );
        }
    }
}
