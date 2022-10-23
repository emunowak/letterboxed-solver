package pl.emunowak.letterboxed.solver.config.dictinary;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import pl.emunowak.letterboxed.solver.config.ConfigurationException;
import pl.emunowak.letterboxed.solver.config.ResourcesConfiguration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

@Configuration
public class EnglishDictionaryConfiguration extends AbstractDictionaryConfiguration {

    @Value("${resource.words.path}")
    private Resource resource;

    public EnglishDictionaryConfiguration( ResourcesConfiguration textResources ) {
        super( textResources );
    }

    @Override
    public Resource getDictionaryResource() {
        return resource;
    }
}
