package tp1.control.commands;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.CommandParseException;
import tp1.exceptions.GameModelException;
import tp1.exceptions.NotAllowedMoveException;
import tp1.logic.GameModel;
import tp1.logic.Move;
import tp1.view.Messages;

public class MoveCommand extends Command {

    private Move move;

    protected MoveCommand() {}

    public MoveCommand(Move move) {
        this.move = move;
    }

    @Override
    protected String getName() {
        return Messages.COMMAND_MOVE_NAME;
    }

    @Override
    protected String getShortcut() {
        return Messages.COMMAND_MOVE_SHORTCUT;
    }

    @Override
    protected String getDetails() {
        return Messages.COMMAND_MOVE_DETAILS;
    }

    @Override
    protected String getHelp() {
        return Messages.COMMAND_MOVE_HELP;
    }

    @Override
    public boolean execute(GameModel game) throws CommandExecuteException {
        try {
            if (this.validMove()) {
                try {
                    return game.move(this.move);
                } catch (GameModelException e) {
                    throw new CommandExecuteException(Messages.MOVEMENT_ERROR, e);
                }
            }
        } catch (NotAllowedMoveException e) {
            throw new CommandExecuteException(Messages.DIRECTION_ERROR.formatted(this.move), e);
        }
        return false;
    }

    @Override
    public Command parse(String[] commandWords) throws CommandParseException {
        if(!this.matchCommandName(commandWords[0]))
            throw new CommandParseException(Messages.UNKNOWN_COMMAND);

        if(commandWords.length == 1)
            throw new CommandParseException(Messages.COMMAND_PARAMETERS_MISSING);
        else if(commandWords.length == 2){
            try {
                this.move = Move.valueOfIgnoreCase(commandWords[1]);
            } catch (IllegalArgumentException e){
                throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_USAGE, e);
            }
        } else throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);

        return this;
    }

    private boolean validMove() throws NotAllowedMoveException {
        boolean valid = (
                this.move == Move.LEFT   ||
                this.move == Move.LLEFT  ||
                this.move == Move.RIGHT  ||
                this.move == Move.RRIGHT
        );
        if(!valid)
            throw new NotAllowedMoveException(Messages.ALLOWED_MOVES_MESSAGE);

        return true;
    }
}