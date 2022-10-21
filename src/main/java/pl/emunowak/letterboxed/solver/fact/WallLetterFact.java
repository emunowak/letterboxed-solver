package pl.emunowak.letterboxed.solver.fact;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WallLetterFact implements Fact {
    private Character letter;
    private int wall;
}
