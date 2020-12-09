package codecamp.bug.wars.ai.service.compiler.components;
import codecamp.bug.wars.ai.service.compiler.models.BugCommand;
import codecamp.bug.wars.ai.service.compiler.models.BugMnemonic;
import codecamp.bug.wars.ai.service.compiler.models.LineOfTokens;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {
    Parser parser;

    @BeforeEach
    public void setup() { parser = new Parser(); }

    @Test
    public void parser_shouldReturnEmptyListIfInputEmpty(){
        assertEquals(Collections.emptyList(), parser.parse(Collections.emptyList()));
    }

    @Test
    public void parser_shouldReturnEmptyListIfInputNull(){
        assertEquals(Collections.emptyList(), parser.parse(null));
    }

    @Test
    public void parseLine_shouldReturnSingleCommand(){
        List<BugCommand> expected = Arrays.asList(new BugCommand(
                null, BugMnemonic.NOOP, null, null
        ));
        assertEquals(expected, parser.parseLine(
                new LineOfTokens(1, "delay"), null
        ));
    }

    @Test
    public void parseLine_shouldReturnTokenWithMultipleCommands(){
        List<BugCommand> expected = Arrays.asList(
                new BugCommand(null, BugMnemonic.BACK, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null)
        );
        assertEquals(expected, parser.parseLine(
                new LineOfTokens(1, "moveBack"), null
        ));
    }

    @Test
    public void parseLine_shouldReturnTokenWithValidatedTargetLabel(){
        List<BugCommand> expected = Arrays.asList(
                new BugCommand(null, BugMnemonic.GOTO, null, "ABC")
        );
        assertEquals(expected, parser.parseLine(
                new LineOfTokens(1, "goto", "ABC"),
                Arrays.asList("ABC")
        ));
    }

    @Test
    public void parseLine_ifEnemy_shouldReturnTokenWithValidatedParameterTargetLabel(){
        List<BugCommand> expected = Arrays.asList(
                new BugCommand(null, BugMnemonic.IF_ENEMY, 10, "ABC")
        );
        assertEquals(expected, parser.parseLine(
                new LineOfTokens(1, "ifEnemy", "10", "ABC"),
                Arrays.asList("ABC")
        ));
    }

    @Test
    public void parser_shouldReturnMneumonicsForSingleCommandsOnMultipleLines(){
        List<LineOfTokens> input = Arrays.asList(
                new LineOfTokens(1, "delay", "attack", "move", "turnLeft"),
                new LineOfTokens(1, "turnRight", "moveBack", "eat", "jump")
        );

        List<BugCommand> expected = Arrays.asList(
                new BugCommand(null, BugMnemonic.NOOP, null, null),
                new BugCommand(null, BugMnemonic.ATTACK, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null),
                new BugCommand(null, BugMnemonic.MOVE, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null),
                new BugCommand(null, BugMnemonic.TURN_LEFT, null, null),
                new BugCommand(null, BugMnemonic.TURN_RIGHT, null, null),
                new BugCommand(null, BugMnemonic.BACK, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null),
                new BugCommand(null, BugMnemonic.EAT, null, null),
                new BugCommand(null, BugMnemonic.JUMP, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null)
        );

        assertEquals(expected, parser.parse(input));
    }

    @Test
    public void parser_shouldReturnMnemonicsForTargetedCommandsOnMultipleLines(){
        List<LineOfTokens> input = Arrays.asList(
                new LineOfTokens(1, "delay", "attack", "move", "turnLeft"),
                new LineOfTokens(1, "turnRight", "moveBack", "eat", "jump")
        );

        List<BugCommand> expected = Arrays.asList(
                new BugCommand(null, BugMnemonic.NOOP, null, null),
                new BugCommand(null, BugMnemonic.ATTACK, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null),
                new BugCommand(null, BugMnemonic.MOVE, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null),
                new BugCommand(null, BugMnemonic.TURN_LEFT, null, null),
                new BugCommand(null, BugMnemonic.TURN_RIGHT, null, null),
                new BugCommand(null, BugMnemonic.BACK, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null),
                new BugCommand(null, BugMnemonic.EAT, null, null),
                new BugCommand(null, BugMnemonic.JUMP, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null)
        );
        assertEquals(expected, parser.parse(input));
    }
}