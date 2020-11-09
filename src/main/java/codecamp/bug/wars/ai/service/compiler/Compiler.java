package codecamp.bug.wars.ai.service.compiler;
import codecamp.bug.wars.ai.model.AIScript;
import codecamp.bug.wars.ai.repository.AiScriptRepository;
import codecamp.bug.wars.ai.service.compiler.components.Assembler;
import codecamp.bug.wars.ai.service.compiler.components.Parser;
import codecamp.bug.wars.ai.service.compiler.components.Tokenizer;
import codecamp.bug.wars.ai.service.compiler.models.BugCommand;
import codecamp.bug.wars.ai.service.compiler.models.LineOfTokens;

import java.util.List;

public class Compiler {
    private final AiScriptRepository repository;

    private Tokenizer tokenizer;
    private Parser parser;
    private Assembler assembler;

    public Compiler(AiScriptRepository repository) {
        this.repository = repository;
    }

    public List<Integer> compile(Long id) {
        AIScript returnScript = repository.findAIById(id);
        String sourceCode = returnScript.getScript();
        List<LineOfTokens> tokenizedScript = tokenizer.tokenize(sourceCode);
        List<BugCommand> parsedScript = parser.parse(tokenizedScript);
        List<Integer> bytecode = assembler.assemble(parsedScript);
        return bytecode;
    }
}
