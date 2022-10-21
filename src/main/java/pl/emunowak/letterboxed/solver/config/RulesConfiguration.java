package pl.emunowak.letterboxed.solver.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class RulesConfiguration {

    private final ResourcesConfiguration textResources;

    @Value("classpath:/rules/*.drl")
    private Resource[] resource;

    public Set<String> getRulesFromResources() {
        Set<String> rules = new HashSet<>();
        for ( var ruleFile : resource ) {
            try ( var inputStream = ruleFile.getInputStream() ) {
                String fileContent = new String( inputStream.readAllBytes(), StandardCharsets.UTF_8 );
                rules.add( fileContent );
            } catch ( IOException e ) {
                log.error( textResources.getRulesFileReadError(), e );
            }
        }
        return rules;
    }
}
