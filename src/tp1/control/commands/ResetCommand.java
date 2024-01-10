package tp1.control.commands;

import tp1.control.InitialConfiguration;
import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.CommandParseException;
import tp1.exceptions.GameModelException;
import tp1.logic.GameModel;
import tp1.view.Messages;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ResetCommand extends Command {

    private InitialConfiguration config;

    protected ResetCommand(){}

    public ResetCommand(InitialConfiguration config){
        this.config = config;
    }

    @Override
    protected String getName() {
        return Messages.COMMAND_RESET_NAME;
    }

    @Override
    protected String getShortcut() {
        return Messages.COMMAND_RESET_SHORTCUT;
    }

    @Override
    protected String getDetails() {
        return Messages.COMMAND_RESET_DETAILS;
    }

    @Override
    protected String getHelp() {
        return Messages.COMMAND_RESET_HELP;
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
            game.reset(this.config);
            return true;
        } catch (GameModelException e){
            throw new CommandExecuteException(Messages.INITIAL_CONFIGURATION_ERROR, e);
        }
    }

    @Override
    public Command parse(String[] commandWords) throws CommandParseException {
        if(!this.matchCommandName(commandWords[0]))
            throw new CommandParseException(Messages.UNKNOWN_COMMAND);

        if(commandWords.length == 1)
            this.config = null;
        else if (commandWords.length == 2) {
            if (commandWords[1].equalsIgnoreCase("none"))
                this.config = InitialConfiguration.NONE;
            else {
                try {
                    this.config = InitialConfiguration.readFromFile(commandWords[1]);
                } catch (FileNotFoundException e) {
                    throw new CommandParseException(Messages.FILE_NOT_FOUND.formatted(commandWords[1]));
                } catch (IOException e) {
                    throw new CommandParseException(Messages.READ_ERROR.formatted(commandWords[1]));
                }
            }
        }
        return this;
    }
}
