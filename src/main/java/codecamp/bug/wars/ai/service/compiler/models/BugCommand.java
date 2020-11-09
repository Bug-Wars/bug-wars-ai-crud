package codecamp.bug.wars.ai.service.compiler.models;
import lombok.Data;

@Data
public class BugCommand {
    private String label;
    private BugMnemonic mnemonic;
    private Integer parameter;
    private String targetLabel;
}

