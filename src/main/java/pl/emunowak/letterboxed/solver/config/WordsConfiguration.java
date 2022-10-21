package pl.emunowak.letterboxed.solver.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

@Configuration
@RequiredArgsConstructor
public class WordsConfiguration {

    private final ResourcesConfiguration textResources;

    @Value("${resource.words.path}")
    private Resource resource;

    public Stream<String> getWordsStream() throws ConfigurationException {
        try {
            return new BufferedReader( new InputStreamReader( resource.getInputStream(), StandardCharsets.UTF_8 ) ).lines();
        } catch ( IOException e ) {
            throw new ConfigurationException( textResources.getWordsFileReadError() );
        }
    }
}
