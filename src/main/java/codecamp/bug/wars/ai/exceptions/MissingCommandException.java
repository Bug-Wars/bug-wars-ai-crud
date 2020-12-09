package codecamp.bug.wars.ai.exceptions;

public class MissingCommandException extends RuntimeException{
    public MissingCommandException(String message){
        super(message);
    }
}
