package codecamp.bug.wars.ai.exceptions;

public class LabelNotFoundException extends RuntimeException {
    public LabelNotFoundException(String message){
        super(message);
    }
}