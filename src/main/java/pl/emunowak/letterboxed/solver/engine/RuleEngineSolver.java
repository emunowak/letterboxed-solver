package pl.emunowak.letterboxed.solver.engine;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.server.Cookie;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pl.emunowak.letterboxed.solver.config.ResourcesConfiguration;
import pl.emunowak.letterboxed.solver.engine.solution.SolutionPart;

import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor
public class RuleEngineSolver implements Solver {

    private final ResourcesConfiguration textResources;
    private Collection<String> words;
    private Collection<String> wallLetters;

    private static final String ONLY_LETTERS_PATTERN = "^[a-zA-Z]+$";

    @Override
    public void initializeDictionary( Collection<String> words ) throws SolverInvalidConfigurationException {
        validateDictionaryWords( words );
        this.words = words;
    }

    @Override
    public void initializeWallLetters( Collection<String> wallLetters ) throws SolverInvalidConfigurationException {
        validateWallLetters( wallLetters );
        this.wallLetters = wallLetters;
    }

    @Override
    public Collection<SolutionPart> solve() throws SolverNotInitializedException {
        if( CollectionUtils.isEmpty( words ) || CollectionUtils.isEmpty( wallLetters ) ) {
            throw new SolverNotInitializedException( textResources.getSolverInitializationError() );
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
