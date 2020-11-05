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

    public AIScript getAi() {
        return ai;
    }

    public void setAi(AIScript ai) {
        this.ai = ai;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

