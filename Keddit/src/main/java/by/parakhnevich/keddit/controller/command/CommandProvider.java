package by.parakhnevich.keddit.controller.command;

import by.parakhnevich.keddit.controller.command.action.get.RegistrationCommandPage;
import by.parakhnevich.keddit.controller.command.action.post.LoginCommand;
import by.parakhnevich.keddit.controller.command.action.post.SignUpCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private static CommandProvider instance = new CommandProvider();
    Map<CommandName, Command> commands;


    private CommandProvider(){
        commands = new HashMap<>();
        commands.put(CommandName.NO_SUCH_COMMAND, null);
        commands.put(CommandName.LOGIN, new LoginCommand());
        commands.put(CommandName.REGISTRATION_PAGE, new RegistrationCommandPage());
        commands.put(CommandName.SIGN_UP, new SignUpCommand());
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
