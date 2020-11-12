package codecamp.bug.wars.ai.service.compiler.components;
import codecamp.bug.wars.ai.service.compiler.models.BugCommand;
import codecamp.bug.wars.ai.service.compiler.models.LineOfTokens;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Parser {
    public List<BugCommand> parse(List<LineOfTokens> tokenizedScript){
        List<BugCommand> result = new ArrayList<>();

        return result;
    }

    public List<BugCommand> parseLine(LineOfTokens tokenLine){
        return null;
    }

}


// extract all labels (if starts with :)
// do something


// check if its a command
// validate command
// does command require parameters
// does command require targetLabel
// validate parameters
// look for targetLabel
    // throw error
// create BugCommand