package pl.emunowak.letterboxed.solver.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:ui.properties")
@ComponentScan("pl.emunowak.letterboxed")
@Getter
public class ResourcesConfiguration {

    @Value( "${error.read.words}" )
    private String wordsFileReadError;

    @Value( "${error.parse.words}")
    private String wordsParseError;

    @Value( "${error.parse.wall.letters}" )
    private String wallLettersParseError;

    @Value( "${error.parse.wall.length}" )
    private String wallLengthParseError;

    @Value( "${error.read.file.rules}" )
    private String rulesFileReadError;

    @Value( "${error.parse.rules}" )
    private String rulesParseError;

    @Value( "${error.prefix}" )
    private String errorPrefix;

    @Value( "${error.solver.initialization}" )
    private String solverInitializationError;

    @Value( "${message.results.header}" )
    private String resultsHeaderMessage;

    @Value( "${message.results.spacer}" )
    private String resultsSpacerMessage;

    @Value( "${message.results.empty}" )
    private String resultsEmptyMessage;

    @Value( "${message.validation.letters}" )
    private String validationLettersMessage;

    @Value( "${message.validation.length}" )
    private String validationLengthMessage;

    @Value( "${validation.length}" )
    private int validationLength;

    @Value( "${message.input.wall.letters}" )
    private String wallLettersInputMessage;

    @Value( "${message.input.walls.max}" )
    private int maxInputWalls;

    @Value( "${format.results.delimiter}" )
    private String resultWordsDelimiter;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
