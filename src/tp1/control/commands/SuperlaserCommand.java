package tp1.control.commands;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.GameModelException;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class SuperlaserCommand extends NoParamsCommand {
    @Override
    protected String getName() {
        return Messages.COMMAND_SUPERLASER_NAME;
    }

    @Override
    protected String getShortcut() {
        return Messages.COMMAND_SUPERLASER_SHORTCUT;
    }

    @Override
    protected String getDetails() {
        return Messages.COMMAND_SUPERLASER_DETAILS;
    }

    @Override
    protected String getHelp() {
        return Messages.COMMAND_SUPERLASER_HELP;
    }

    /**
     * Execute the command.
     *
     * @param game Where to execute the command.
     * @return {@code ExecutionResult} representing if command was successful and if board must be printed
     */
    @Override
    public boolean execute(GameModel game) throws CommandExecuteException {
        try {
            return game.shootSuperLaser();
        } catch (GameModelException e){
            throw new CommandExecuteException(Messages.SUPERLASER_ERROR, e);
        }
    }
}
