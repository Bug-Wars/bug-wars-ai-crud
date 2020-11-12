package codecamp.bug.wars.ai.service.compiler.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BugCommand {
    private String label;
    private BugMnemonic mnemonic;
    private Integer parameter;
    private String targetLabel;
}

