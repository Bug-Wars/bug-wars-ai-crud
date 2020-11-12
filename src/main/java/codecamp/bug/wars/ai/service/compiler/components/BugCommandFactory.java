package codecamp.bug.wars.ai.service.compiler.components;
import codecamp.bug.wars.ai.service.compiler.models.BugCommand;
import codecamp.bug.wars.ai.service.compiler.models.BugMnemonic;

import java.util.ArrayList;
import java.util.List;

public class BugCommandFactory {

    public List<BugCommand> create(String command) {
        return create(command, null);
    }

    public List<BugCommand> create(String command, Integer parameter) {
        List<BugCommand> result = new ArrayList<>();
        switch (command) {
            case "delay":
                result.add(new BugCommand(null, BugMnemonic.NOOP, null, null));
                break;
            case "attack":
                result.add(new BugCommand(null, BugMnemonic.ATTACK, null, null));
                result.add(new BugCommand(null, BugMnemonic.NOOP, null, null));
                result.add(new BugCommand(null, BugMnemonic.NOOP, null, null));
                break;
            case "move":
                result.add(new BugCommand(null, BugMnemonic.MOVE, null, null));
                result.add(new BugCommand(null, BugMnemonic.NOOP, null, null));
                break;
            case "turnLeft":
                result.add(new BugCommand(null, BugMnemonic.TURN_LEFT, null, null));
                break;
            case "turnRight":
                result.add(new BugCommand(null, BugMnemonic.TURN_RIGHT, null, null));
                break;
            case "moveBack":
                result.add(new BugCommand(null, BugMnemonic.BACK, null, null));
                result.add(new BugCommand(null, BugMnemonic.NOOP, null, null));
                result.add(new BugCommand(null, BugMnemonic.NOOP, null, null));
                break;
            case "eat":
                result.add(new BugCommand(null, BugMnemonic.NOOP, null, null));
                result.add(new BugCommand(null, BugMnemonic.NOOP, null, null));
                result.add(new BugCommand(null, BugMnemonic.NOOP, null, null));
                result.add(new BugCommand(null, BugMnemonic.EAT, null, null));
                break;
            case "jump":
                result.add(new BugCommand(null, BugMnemonic.JUMP, null, null));
                result.add(new BugCommand(null, BugMnemonic.NOOP, null, null));
                result.add(new BugCommand(null, BugMnemonic.NOOP, null, null));
                break;
            case "ifFriend":
                if (parameter == null){ parameter = 1; }
                result.add(new BugCommand(null, BugMnemonic.IF_FRIEND, parameter, null));
                break;
            case "ifWall":
                if (parameter == null){ parameter = 1; }
                result.add(new BugCommand(null, BugMnemonic.IF_WALL, parameter, null));
                break;
        }
        return result;
    }
}
