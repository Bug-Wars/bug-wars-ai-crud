package codecamp.bug.wars.ai.service.compiler.components;
import codecamp.bug.wars.ai.exceptions.InvalidLabelException;
import codecamp.bug.wars.ai.service.compiler.models.LineOfTokens;

import java.util.ArrayList;
import java.util.List;

public class LabelValidator {
    public List<String> mapLabels(List<LineOfTokens> input){
        List<String> result = new ArrayList<>();

        if(input == null){
            return result;
        }

        for (LineOfTokens line : input){
            for (int i = 0; i<line.getTokens().size(); i++){
                if (line.getTokens().get(i).startsWith(":")) {
                    if(i > 0){
                        throw new InvalidLabelException("labels must be at the of a start a line");
                    }
                    else if(!(line.getTokens().get(i).substring(1).matches("^[A-Z0-9]+$"))){
                        throw new InvalidLabelException("labels must only consist of capital letters and numbers");
                    }
                    result.add(line.getTokens().get(i).substring(1));
                }
            }
        }
        return result;
    }
}
