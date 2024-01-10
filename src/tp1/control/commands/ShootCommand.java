package tp1.control.commands;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.CommandParseException;
import tp1.exceptions.GameModelException;
import tp1.exceptions.NotAllowedMoveException;
import tp1.logic.GameModel;
import tp1.logic.Move;
import tp1.view.Messages;

public class ShootCommand extends Command {
    private Move direction;

    protected ShootCommand() {}

    public ShootCommand(Move direction) {
        this.direction = direction;
    }

    @Override
    protected String getName() {
        return Messages.COMMAND_SHOOT_NAME;
    }

    @Override
    protected String getShortcut() {
        return Messages.COMMAND_SHOOT_SHORTCUT;
    }

    @Override
    protected String getDetails() {
        return Messages.COMMAND_SHOOT_DETAILS;
    }

    @Override
    protected String getHelp() {
        return Messages.COMMAND_SHOOT_HELP;
    }

    /**
     * Execute the command.
     *
     * @param game Where to execute the command.
     * @return {@code ExecutionResult} representing if command was successful and if board must be printed
     */
    @Override
    public boolean execute(GameModel game) throws CommandExecuteException {
        boolean result = false;
        try {
            if(validMoves()) {
                try {
                    result = game.shootLaser(this.direction);
                } catch (GameModelException e) {
                    throw new CommandExecuteException(Messages.LASER_ERROR, e);
                }
            }
        } catch (NotAllowedMoveException e) {
            throw new CommandExecuteException(Messages.DIRECTION_ERROR.formatted(this.direction), e);
        }
        return result;
    }

    @Override
    public Command parse(String[] commandWords) throws CommandParseException {
        if(!this.matchCommandName(commandWords[0]))
            throw new CommandParseException(Messages.UNKNOWN_COMMAND);

        if(commandWords.length == 1)
            this.direction = Move.UP;
        else if(commandWords.length == 2){
            try {
                this.direction = Move.valueOfIgnoreCase(commandWords[1]);
            } catch (IllegalArgumentException e){
                throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_USAGE, e);
            }
        } else throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);

        return this;
    }

    private boolean validMoves() throws NotAllowedMoveException{
        boolean valid = (
            this.direction == Move.UP   ||
            this.direction == Move.LUP  ||
            this.direction == Move.RUP
        );
        if(!valid)
            throw new NotAllowedMoveException(Messages.ALLOWED_LASER_MOVES_MESSAGE);

        return true;
    }
}
