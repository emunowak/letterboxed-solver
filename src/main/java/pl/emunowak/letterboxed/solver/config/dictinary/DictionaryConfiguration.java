package pl.emunowak.letterboxed.solver.config.dictinary;

import pl.emunowak.letterboxed.solver.config.ConfigurationException;

import java.util.stream.Stream;

public interface DictionaryConfiguration {
    Stream<String> getWordsStream() throws ConfigurationException;
}
