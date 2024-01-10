package tp1.exceptions;

public class CommandExecuteException extends Exception {
    public CommandExecuteException(){}
    public CommandExecuteException(String message){
        super(message);
    }
    public CommandExecuteException(String message, Throwable cause){
        super(message, cause);
    }
    public CommandExecuteException(Throwable cause){
        super(cause);
    }
}
