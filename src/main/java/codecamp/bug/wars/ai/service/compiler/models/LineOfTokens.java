package codecamp.bug.wars.ai.service.compiler.models;
import lombok.Data;
import java.util.List;

@Data
public class LineOfTokens {
    private Integer row;
    private List<String> tokens;
}
