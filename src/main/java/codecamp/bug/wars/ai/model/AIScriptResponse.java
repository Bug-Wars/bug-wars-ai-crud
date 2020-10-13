package codecamp.bug.wars.ai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AIScriptResponse {
    private AIScript ai;
    private String error;
}

