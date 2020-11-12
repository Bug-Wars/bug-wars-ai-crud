package codecamp.bug.wars.ai.service.compiler.components;
import codecamp.bug.wars.ai.service.compiler.models.BugCommand;
import codecamp.bug.wars.ai.service.compiler.models.BugMnemonic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BugCommandFactoryTest {
    BugCommandFactory bugCommandFactory;

    @BeforeEach
    public void setup() {
        bugCommandFactory = new BugCommandFactory();
    }

    @Test
    public void create_delay_shouldReturnNOOP() {
        List<BugCommand> res = bugCommandFactory.create("delay");
        List<BugCommand> expected = Arrays.asList(
                new BugCommand(null, BugMnemonic.NOOP, null, null)
        );
        assertEquals(expected, res);
    }

    @Test
    public void create_attack_shouldReturnATTACK_NOOP_NOOP() {
        List<BugCommand> res = bugCommandFactory.create("attack");
        List<BugCommand> expected = Arrays.asList(
                new BugCommand(null, BugMnemonic.ATTACK, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null)
        );
        assertEquals(expected, res);
    }

    @Test
    public void create_move_shouldReturnMOVE_NOOP() {
        List<BugCommand> res = bugCommandFactory.create("move");
        List<BugCommand> expected = Arrays.asList(
                new BugCommand(null, BugMnemonic.MOVE, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null)
                );
        assertEquals(expected, res);
    }

    @Test
    public void create_turnLeft_shouldReturnTURN_LEFT() {
        List<BugCommand> res = bugCommandFactory.create("turnLeft");
        List<BugCommand> expected = Arrays.asList(
                new BugCommand(null, BugMnemonic.TURN_LEFT, null, null)
        );
        assertEquals(expected, res);
    }

    @Test
    public void create_turnRight_shouldReturnTURN_RIGHT() {
        List<BugCommand> res = bugCommandFactory.create("turnRight");
        List<BugCommand> expected = Arrays.asList(
                new BugCommand(null, BugMnemonic.TURN_RIGHT, null, null)
        );
        assertEquals(expected, res);
    }

    @Test
    public void create_moveBack_shouldReturnBACK_NOOP_NOOP() {
        List<BugCommand> res = bugCommandFactory.create("moveBack");
        List<BugCommand> expected = Arrays.asList(
                new BugCommand(null, BugMnemonic.BACK, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null)

        );
        assertEquals(expected, res);
    }

    @Test
    public void create_eat_shouldReturnNOOP_NOOP_NOOP_EAT() {
        List<BugCommand> res = bugCommandFactory.create("eat");
        List<BugCommand> expected = Arrays.asList(
                new BugCommand(null, BugMnemonic.NOOP, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null),
                new BugCommand(null, BugMnemonic.EAT, null, null)
        );
        assertEquals(expected, res);
    }

    @Test
    public void create_jump_shouldReturnJUMP_NOOP_NOOP() {
        List<BugCommand> res = bugCommandFactory.create("jump");
        List<BugCommand> expected = Arrays.asList(
                new BugCommand(null, BugMnemonic.JUMP, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null),
                new BugCommand(null, BugMnemonic.NOOP, null, null)
        );
        assertEquals(expected, res);
    }

    @Test
    public void create_ifFriend_shouldReturnBugCommandWithParameter() {
        List<BugCommand> res = bugCommandFactory.create("ifFriend", 10);
        List<BugCommand> expected = Arrays.asList(
                new BugCommand(null, BugMnemonic.IF_FRIEND, 10, null)
        );
        assertEquals(expected, res);
    }

    @Test
    public void create_ifFriend_shouldReturnBugCommandWithDefaultParameter() {
        List<BugCommand> res = bugCommandFactory.create("ifFriend", null);
        List<BugCommand> expected = Arrays.asList(
                new BugCommand(null, BugMnemonic.IF_FRIEND, 1, null)
        );
        assertEquals(expected, res);
    }

    @Test
    public void create_ifWall_shouldReturnBugCommandWithParameter() {
        List<BugCommand> res = bugCommandFactory.create("ifWall", 10);
        List<BugCommand> expected = Arrays.asList(
                new BugCommand(null, BugMnemonic.IF_WALL, 10, null)
        );
        assertEquals(expected, res);
    }

    @Test
    public void create_ifWall_shouldReturnBugCommandWithDefaultParameter() {
        List<BugCommand> res = bugCommandFactory.create("ifWall", null);
        List<BugCommand> expected = Arrays.asList(
                new BugCommand(null, BugMnemonic.IF_WALL, 1, null)
        );
        assertEquals(expected, res);
    }






}