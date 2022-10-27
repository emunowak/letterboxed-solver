package pl.emunowak.letterboxed.solver.engine.solution;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LetterWall {
    private Collection<String> words;
    private Collection<String> walls;
}
