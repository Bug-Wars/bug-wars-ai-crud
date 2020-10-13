package codecamp.bug.wars.ai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AIScriptResponse {

    private Long id;
    private String name;
    private String script;
    private String error;
}
