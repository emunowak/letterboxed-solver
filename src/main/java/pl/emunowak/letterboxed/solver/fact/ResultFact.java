package pl.emunowak.letterboxed.solver.fact;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResultFact implements Fact {
    private List<String> words;

    public int getWordsCount() {
        return words.size();
    }
}
