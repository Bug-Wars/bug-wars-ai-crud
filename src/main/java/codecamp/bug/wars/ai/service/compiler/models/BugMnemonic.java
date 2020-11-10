package codecamp.bug.wars.ai.service.compiler.models;

public enum BugMnemonic {
    NOOP(0),
    ATTACK(1),
    MOVE(2),
    TURN_LEFT(3),
    TURN_RIGHT(4),
    BACK(5),
    EAT(6),
    JUMP(7),
    GOTO(20),
    IF_FRIEND(21),
    IF_WALL(22),
    IF_FOOD(23),
    IF_ENEMY(24);

    private int objectCodeRepresentation;

    public int getObjectCodeRepresentation() { return objectCodeRepresentation; }

    private BugMnemonic(int objectCodeRepresentation) {
        this.objectCodeRepresentation = objectCodeRepresentation;
    }

}