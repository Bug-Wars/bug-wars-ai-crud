package codecamp.bug.wars.ai.service.compiler.components;
import codecamp.bug.wars.ai.service.compiler.models.BugCommand;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Assembler {
    public List<Integer> assemble(List<BugCommand> abstractProgram){
        List<Integer> byteCode = new ArrayList<>();
        Map<String, Integer> labelMap = new HashMap<String, Integer>();
        Integer index = 0;

        for (BugCommand command: abstractProgram) {
            if (command.getLabel() != null) {
                labelMap.put(command.getLabel(), index);
            }
            if (command.getParameter() != null) {
                index++;
            }
            if (command.getTargetLabel() != null){
                index++;
            }
            index++;
        }

        for (BugCommand command: abstractProgram) {
            byteCode.add(command.getMnemonic().getOpCode());
            if (command.getParameter() != null){
                byteCode.add(command.getParameter());
            }
            if (command.getTargetLabel() != null){
                byteCode.add(labelMap.get(command.getTargetLabel()));
            }
        }
        return byteCode;
    }
}
