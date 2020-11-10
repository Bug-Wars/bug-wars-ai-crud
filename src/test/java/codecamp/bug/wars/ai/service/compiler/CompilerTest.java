package codecamp.bug.wars.ai.service.compiler;

import codecamp.bug.wars.ai.exceptions.AIScriptNotFoundException;
import codecamp.bug.wars.ai.exceptions.InvalidInputException;
import codecamp.bug.wars.ai.exceptions.ParseErrorException;
import codecamp.bug.wars.ai.model.AIScript;
import codecamp.bug.wars.ai.repository.AiScriptRepository;
import codecamp.bug.wars.ai.service.compiler.components.Assembler;
import codecamp.bug.wars.ai.service.compiler.components.Parser;
import codecamp.bug.wars.ai.service.compiler.components.Tokenizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CompilerTest {
    AiScriptRepository mockRepository;
    Tokenizer mockTokenizer;
    Parser mockParser;
    Assembler mockAssembler;
    Compiler compiler;

    @BeforeEach
    public void setup() {
        mockRepository = Mockito.mock(AiScriptRepository.class);
        mockTokenizer = Mockito.mock(Tokenizer.class);
        mockParser = Mockito.mock(Parser.class);
        mockAssembler = Mockito.mock(Assembler.class);
        compiler = new Compiler(mockRepository, mockTokenizer, mockParser, mockAssembler);
    }

    @Test
    public void compile_ScriptNotFoundShouldThrowError() {
        Mockito.when(mockRepository.findById(1L)).thenReturn(Optional.empty());

        // assert
        AIScriptNotFoundException exception = assertThrows(AIScriptNotFoundException.class, () -> {
            // act
            compiler.compile(1L);
        });
        // assert
        assertEquals("There is no AIScript with that ID.", exception.getMessage());
    }

    @Test
    public void compile_ParseFailureShouldBubbleUpError() {
        Mockito.when(mockRepository.findById(1L)).thenReturn(Optional.of(new AIScript(1L, null, null)));
        Mockito.when(mockParser.parse(Mockito.any())).thenThrow(new ParseErrorException("Test"));

        // assert
        ParseErrorException exception = assertThrows(ParseErrorException.class, () -> {
            // act
            compiler.compile(1L);
        });

        // assert
        assertEquals("Test", exception.getMessage());
    }


}
