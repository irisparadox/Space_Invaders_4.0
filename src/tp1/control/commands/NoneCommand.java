package tp1.control.commands;

import tp1.exceptions.CommandExecuteException;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class NoneCommand extends NoParamsCommand {
    @Override
    protected String getName() {
        return Messages.COMMAND_NONE_NAME;
    }

    @Override
    protected String getShortcut() {
        return Messages.COMMAND_NONE_SHORTCUT;
    }

    @Override
    protected String getDetails() {
        return Messages.COMMAND_NONE_DETAILS;
    }

    @Override
    protected String getHelp() {
        return Messages.COMMAND_NONE_HELP;
    }

    /**
     * Execute the command.
     *
     * @param game Where to execute the command.
     * @return {@code ExecutionResult} representing if command was successful and if board must be printed
     */
    @Override
    public boolean execute(GameModel game) throws CommandExecuteException {
        return true;
    }

    @Override
    protected boolean matchCommandName(String name){
        return getShortcut().equalsIgnoreCase(name) ||
                getName().equalsIgnoreCase(name) ||
                "".equalsIgnoreCase(name);
    }
}
