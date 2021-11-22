package by.parakhnevich.keddit.controller.command;

import by.parakhnevich.keddit.controller.command.action.edit.CreatePublicationByUserCommand;
import by.parakhnevich.keddit.controller.command.action.redirect.*;
import by.parakhnevich.keddit.controller.command.action.verify.LoginCommand;
import by.parakhnevich.keddit.controller.command.action.edit.SignUpCommand;

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
        commands.put(CommandName.LOGIN_PAGE, new LoginCommandPage());
        commands.put(CommandName.PUBLICATIONS, new PublicationsCommandPage());
        commands.put(CommandName.USER_PAGE, new UserCommandPage());
        commands.put(CommandName.COMMUNITY_PAGE, new CommunityCommandPage());
        commands.put(CommandName.FOLLOWERS, new FollowersCommandPage());
        commands.put(CommandName.SEARCH_PAGE, new SearchCommandPage());
        commands.put(CommandName.CREATE_PUBLICATION_BY_USER, new CreatePublicationByUserCommand());
        commands.put(CommandName.CREATE_PUBLICATION_BY_USER_PAGE, new CreatePublicationByUserCommandPage());
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
