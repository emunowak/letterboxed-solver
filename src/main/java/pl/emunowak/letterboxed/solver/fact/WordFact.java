package pl.emunowak.letterboxed.solver.fact;

import lombok.Data;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
public class WordFact implements Fact {

    private String word;
    private Collection<Character> letters;

    public WordFact( String word ) {
        this.word = word.toUpperCase();
        this.letters = this.word.chars().mapToObj( c -> ( char ) c ).collect( Collectors.toList() );
    }

    public int getLength() {
        return word.length();
    }
}