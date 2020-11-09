package codecamp.bug.wars.ai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CompiledScriptResponse {
    private Long id;
    private List<Integer> bytecode;
    private String error;
}