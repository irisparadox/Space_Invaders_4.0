package tp1.control.commands;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.GameModelException;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class ShockwaveCommand extends NoParamsCommand {
    @Override
    protected String getName() {
        return Messages.COMMAND_SHOCKWAVE_NAME;
    }

    @Override
    protected String getShortcut() {
        return Messages.COMMAND_SHOCKWAVE_SHORTCUT;
    }

    @Override
    protected String getDetails() {
        return Messages.COMMAND_SHOCKWAVE_DETAILS;
    }

    @Override
    protected String getHelp() {
        return Messages.COMMAND_SHOCKWAVE_HELP;
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
            return game.performShockwaveAttack();
        } catch (GameModelException e) {
            throw new CommandExecuteException(Messages.SHOCKWAVE_ERROR,e);
        }
    }
}
