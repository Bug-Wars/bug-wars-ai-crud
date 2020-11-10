package codecamp.bug.wars.ai.service.compiler.components;
import codecamp.bug.wars.ai.service.compiler.models.BugCommand;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Assembler {
    public List<Integer> assemble(List<BugCommand> abstractProgram){
        List<Integer> byteCode = new ArrayList<>();
        for (BugCommand command: abstractProgram) {
            byteCode.add(command.getMnemonic().getObjectCodeRepresentation());
        }
        return byteCode;
    }
}
