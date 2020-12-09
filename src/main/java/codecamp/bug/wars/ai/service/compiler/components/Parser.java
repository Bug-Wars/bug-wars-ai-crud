package codecamp.bug.wars.ai.service.compiler.components;
import codecamp.bug.wars.ai.exceptions.InvalidInputException;
import codecamp.bug.wars.ai.exceptions.InvalidParameterException;
import codecamp.bug.wars.ai.exceptions.LabelNotFoundException;
import codecamp.bug.wars.ai.exceptions.MissingCommandException;
import codecamp.bug.wars.ai.service.compiler.models.BugCommand;
import codecamp.bug.wars.ai.service.compiler.models.LineOfTokens;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class Parser {

    BugCommandFactory bugCommandFactory = new BugCommandFactory();
    LabelValidator labelValidator = new LabelValidator();

    public List<BugCommand> parse(List<LineOfTokens> tokenizedScript){
        List<BugCommand> result = new ArrayList<>();

        if(tokenizedScript == null){
            return result;
        }

        List<String> labels = labelValidator.mapLabels(tokenizedScript);

        for(LineOfTokens line : tokenizedScript){
            result.addAll(parseLine(line, labels));
        }
        return result;
    }

    public List<BugCommand> parseLine(LineOfTokens tokenLine, List<String> labels) {
        List<BugCommand> result = new ArrayList<>();
        String lineLabel = null;
        int counter = 0;

        if(tokenLine.getTokens().get(0).startsWith(":")){
            if(tokenLine.getTokens().size() < 2){
                throw new MissingCommandException("Label " + tokenLine.getTokens().get(0) + " needs to be followed by at least one command");
            }
            lineLabel = tokenLine.getTokens().get(0).substring(1);
            counter++;
        }

        while (counter<tokenLine.getTokens().size()){
            String token = tokenLine.getTokens().get(counter);
            String targetLabel;
            Integer param;
            List<BugCommand> tmp;

            switch(token) {
                case "delay":
                case "attack":
                case "move":
                case "turnLeft":
                case "turnRight":
                case "moveBack":
                case "eat":
                case "jump":
                    result.addAll(bugCommandFactory.create(token));
                    break;
                case "goto":
                    if(counter >= tokenLine.getTokens().size()-1){
                        throw new IllegalArgumentException("Command " + token + " requires at least one argument");
                    }
                    counter++;
                    targetLabel = tokenLine.getTokens().get(counter);

                    if (!labels.contains(targetLabel)){
                        throw new LabelNotFoundException("Label does not exist: " + targetLabel);
                    }

                    tmp = bugCommandFactory.create(token);
                    tmp.get(0).setTargetLabel(targetLabel);
                    result.addAll(tmp);
                    break;
                case "ifFriend":
                case "ifWall":
                case "ifFood":
                case "ifEnemy":
                    if(tokenLine.getTokens().size() - counter <= 1){
                        throw new IllegalArgumentException("Command " + token + " requires at least one argument");
                    }
                    counter++;
                    param = Integer.parseInt(tokenLine.getTokens().get(counter));

                    try{
                        param = Integer.parseInt(tokenLine.getTokens().get(counter));
                        counter++;
                    } catch (Exception e) {
                        param = 1;
                    }

                    if(param < 0){
                        throw new InvalidParameterException("Invalid parameter: " + param);
                    }

                    targetLabel = tokenLine.getTokens().get(counter);

                    if (!labels.contains(targetLabel)){
                        throw new LabelNotFoundException("Label does not exist: " + targetLabel);
                    }

                    tmp = bugCommandFactory.create(token, param);
                    tmp.get(0).setTargetLabel(targetLabel);
                    result.addAll(tmp);
                    break;
                default:
                    throw new InvalidInputException(token + " is not a valid command.");
            }
            counter++;
        }
        if(lineLabel != null){
            result.get(0).setTargetLabel(lineLabel);
        }
        return result;
    }
}
