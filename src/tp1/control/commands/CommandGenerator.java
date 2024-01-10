package tp1.control.commands;

import tp1.exceptions.CommandParseException;
import tp1.view.Messages;

import java.util.Arrays;
import java.util.List;

public class CommandGenerator {

    private static final List<Command> availableCommands = Arrays.asList(
            new HelpCommand(),
            new ListCommand(),
            new MoveCommand(),
            new ShootCommand(),
            new SuperlaserCommand(),
            new ShockwaveCommand(),
            new ResetCommand(),
            new NoneCommand(),
            new ExitCommand()
    );

    public static Command parse(String[] commandWords) throws CommandParseException {
        for (Command c: availableCommands) {
            if(c.matchCommandName(commandWords[0]))
                return c.parse(commandWords);
        }
        throw new CommandParseException(Messages.UNKNOWN_COMMAND);
    }

    public static String commandHelp() {
        StringBuilder commands = new StringBuilder();
        for (Command c: availableCommands) {
            System.out.println(Messages.commandDescription(c.getDetails(),c.getHelp()));
        }
        return commands.toString();
    }

}