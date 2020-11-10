package codecamp.bug.wars.ai.service.compiler.models;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BugCommand {
    private String label;
    private BugMnemonic mnemonic;
    private Integer parameter;
    private String targetLabel;
}

