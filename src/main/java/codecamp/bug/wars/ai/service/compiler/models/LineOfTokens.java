package codecamp.bug.wars.ai.service.compiler.models;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
public class LineOfTokens {
    private Integer row;
    private List<String> tokens;

    public LineOfTokens(Integer row, String... tokens){
        this.row = row;
        if (tokens != null){
            this.tokens = Arrays.asList(tokens);
        }
    }
}


