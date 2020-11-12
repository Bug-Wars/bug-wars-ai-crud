package codecamp.bug.wars.ai.service.compiler.components;
import codecamp.bug.wars.ai.service.compiler.models.LineOfTokens;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class Tokenizer {
    public List<LineOfTokens> tokenize(String script) {
        List<LineOfTokens> result = new ArrayList<>();

        if (script == null) {
            return result;
        }

        String[] lines = script.split("\\n");

        Integer row = 1;
        for (String line : lines){

            if (line == null || line.trim().equals("") || line.trim().startsWith("#")) {
                row++;
                continue;
            }

            if (line.contains("#")){
                Integer hashIndex = line.indexOf("#");
                line = line.substring(0, hashIndex);
            }

            String[] tokens = line.trim().split("\\s+");
            LineOfTokens temp = new LineOfTokens(row, Arrays.asList(tokens));
            result.add(temp);
            row++;
        }

        return result;
    }
}