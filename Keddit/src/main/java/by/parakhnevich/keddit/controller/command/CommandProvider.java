package by.parakhnevich.keddit.controller.command;

import by.parakhnevich.keddit.controller.command.action.post.LoginCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private static CommandProvider instance = new CommandProvider();
    Map<CommandName, Command> commands;


    private CommandProvider(){
        commands = new HashMap<>();
        commands.put(CommandName.NO_SUCH_COMMAND, null);
        commands.put(CommandName.LOGIN, new LoginCommand());
    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command getCommand(String commandName) {
        CommandName name = CommandName.valueOf(commandName.toUpperCase());
        if (name != null) {
            return commands.get(name);
        }
        return commands.get(CommandName.NO_SUCH_COMMAND);
    }
}
