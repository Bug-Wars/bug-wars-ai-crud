package codecamp.bug.wars.ai.service.compiler.components;

import codecamp.bug.wars.ai.service.compiler.models.BugCommand;
import codecamp.bug.wars.ai.service.compiler.models.BugMnemonic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static codecamp.bug.wars.ai.service.compiler.models.BugMnemonic.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


class AssemblerTest {
    Assembler assembler;
    @BeforeEach()
    public void setup(){
        assembler = new Assembler();

    }
    @Test
    public void assemble_shouldReturnEmptyListWhenGivenEmptyProgram(){
        //arrange
        List<BugCommand> input = Collections.emptyList();

        //act
        List<Integer> objectCode = assembler.assemble(input);

        //assert
        assertEquals(Collections.emptyList(), objectCode);
    }
    private List<Integer> getSingleObjectCode(BugMnemonic mnemonic){
        List<BugCommand> input = Arrays.asList(new BugCommand(null, mnemonic, null, null));
        //act
        return assembler.assemble(input);
    }

    @Test
    public void assemble_shouldReturnObjectCodeForAllCommands(){
        assertEquals(Arrays.asList(0),getSingleObjectCode(NOOP));
        assertEquals(Arrays.asList(1),getSingleObjectCode(ATTACK));
        assertEquals(Arrays.asList(2),getSingleObjectCode(MOVE));
        assertEquals(Arrays.asList(3),getSingleObjectCode(TURN_LEFT));
        assertEquals(Arrays.asList(4),getSingleObjectCode(TURN_RIGHT));
        assertEquals(Arrays.asList(5),getSingleObjectCode(BACK));
        assertEquals(Arrays.asList(6),getSingleObjectCode(EAT));
        assertEquals(Arrays.asList(7),getSingleObjectCode(JUMP));
    }

    @Test
    public void assemble_shouldReturnAnArrayOfObjectCodeGivenMultipleCommands(){
        //arrange
        List<BugCommand> input = Arrays.asList(
                new BugCommand(null, NOOP, null, null),
                new BugCommand(null, ATTACK, null, null),
                new BugCommand(null, MOVE, null, null),
                new BugCommand(null, TURN_LEFT, null, null),
                new BugCommand(null, TURN_RIGHT, null, null),
                new BugCommand(null, BACK, null, null),
                new BugCommand(null, EAT, null, null),
                new BugCommand(null, JUMP, null, null)

                );

        //act
        List<Integer> objectCode = assembler.assemble(input);

        //assert
        assertEquals(Arrays.asList(0,1,2,3,4,5,6,7), objectCode);
    }







}