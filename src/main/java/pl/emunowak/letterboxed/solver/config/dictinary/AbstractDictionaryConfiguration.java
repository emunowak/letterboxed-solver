package pl.emunowak.letterboxed.solver.config.dictinary;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import pl.emunowak.letterboxed.solver.config.ConfigurationException;
import pl.emunowak.letterboxed.solver.config.ResourcesConfiguration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

@RequiredArgsConstructor
public abstract class AbstractDictionaryConfiguration implements DictionaryConfiguration {

    private final ResourcesConfiguration textResources;

    public abstract Resource getDictionaryResource();

    public Stream<String> getWordsStream() throws ConfigurationException {
        try {
            return new BufferedReader( new InputStreamReader(
                    getDictionaryResource().getInputStream(), StandardCharsets.UTF_8 ) ).lines();
        } catch ( IOException e ) {
            throw new ConfigurationException( textResources.getWordsFileReadError() );
        }
    }
}
