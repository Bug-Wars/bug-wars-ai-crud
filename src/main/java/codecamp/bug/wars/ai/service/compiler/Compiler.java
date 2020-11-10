package codecamp.bug.wars.ai.service.compiler;
import codecamp.bug.wars.ai.exceptions.AIScriptNotFoundException;
import codecamp.bug.wars.ai.model.AIScript;
import codecamp.bug.wars.ai.repository.AiScriptRepository;
import codecamp.bug.wars.ai.service.compiler.components.Assembler;
import codecamp.bug.wars.ai.service.compiler.components.Parser;
import codecamp.bug.wars.ai.service.compiler.components.Tokenizer;
import codecamp.bug.wars.ai.service.compiler.models.BugCommand;
import codecamp.bug.wars.ai.service.compiler.models.LineOfTokens;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Compiler {
    private final AiScriptRepository repository;
    private Tokenizer tokenizer;
    private Parser parser;
    private Assembler assembler;

    public Compiler(AiScriptRepository repository, Tokenizer tokenizer, Parser parser, Assembler assembler) {
        this.repository = repository;
        this.tokenizer = tokenizer;
        this.parser = parser;
        this.assembler = assembler;
    }

    public List<Integer> compile(Long id) {
        Optional<AIScript> returnScriptOptional = repository.findById(id);

        if(returnScriptOptional.isEmpty()){
            throw new AIScriptNotFoundException("There is no AIScript with that ID.");
        }

        String sourceCode = returnScriptOptional.get().getScript();
        List<LineOfTokens> tokenizedScript = tokenizer.tokenize(sourceCode);
        List<BugCommand> parsedScript = parser.parse(tokenizedScript);
        List<Integer> objectCode = assembler.assemble(parsedScript);

        return objectCode;
    }
}
