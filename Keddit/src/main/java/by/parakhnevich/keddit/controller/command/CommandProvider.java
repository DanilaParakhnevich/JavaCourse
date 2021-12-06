package by.parakhnevich.keddit.controller.command;

import by.parakhnevich.keddit.controller.command.action.edit.*;
import by.parakhnevich.keddit.controller.command.action.edit.rating.*;
import by.parakhnevich.keddit.controller.command.action.redirect.*;
import by.parakhnevich.keddit.controller.command.action.verify.LoginCommand;
import by.parakhnevich.keddit.controller.command.action.verify.LogoutCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * The class CommandProvider that provide commands to
 * controller depending on command names.
 * @see CommandName
 * @see by.parakhnevich.keddit.controller.KedditController
 * @see Command
 * @author Danila Parakhnevich
 */
public class CommandProvider {
    private static final CommandProvider instance = new CommandProvider();
    private Map<CommandName, Command> commands;


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
        commands.put(CommandName.CREATE_PUBLICATION_BY_COMMUNITY, new CreatePublicationByCommunityCommand());
        commands.put(CommandName.CREATE_PUBLICATION_BY_COMMUNITY_PAGE, new CreatePublicationByCommunityCommandPage());
        commands.put(CommandName.PUBLICATION_PAGE, new PublicationCommandPage());
        commands.put(CommandName.ABOUT, new AboutCommandPage());
        commands.put(CommandName.SEARCH_FOLLOWER, new SearchFollowersCommand());
        commands.put(CommandName.MY_COMMUNITIES, new MyCommunitiesCommandPage());
        commands.put(CommandName.LOGOUT, new LogoutCommand());
        commands.put(CommandName.SET_LIKE_PUBLICATION, new LikePublicationCommand());
        commands.put(CommandName.SET_DISLIKE_PUBLICATION, new DislikePublicationCommand());
        commands.put(CommandName.SUBSCRIBE_COMMUNITY, new SubscribeCommand());
        commands.put(CommandName.SET_LIKE_COMMENT, new LikeCommentCommand());
        commands.put(CommandName.SET_DISLIKE_COMMENT, new DislikeCommentCommand());
        commands.put(CommandName.EDIT_PUBLICATION_PAGE, new EditPublicationCommandPage());
        commands.put(CommandName.EDIT_PUBLICATION, new EditPublicationCommand());
        commands.put(CommandName.DELETE_USER, new DeleteUserCommand());
        commands.put(CommandName.DELETE_COMMENT, new DeleteCommentCommand());
        commands.put(CommandName.DELETE_PUBLICATION, new DeletePublicationCommand());
        commands.put(CommandName.CREATE_COMMENT, new CreateCommentCommand());
        commands.put(CommandName.EDIT_USER_PAGE, new EditUserCommandPage());
        commands.put(CommandName.EDIT_USER, new EditUserCommand());
        commands.put(CommandName.BLOCK_USER, new BlockUserCommand());
        commands.put(CommandName.EDIT_ROLE_PAGE, new EditRoleCommandPage());
        commands.put(CommandName.EDIT_ROLE, new EditRoleCommand());
        commands.put(CommandName.CHANGE_LANG, new ChangeLanguageCommand());
        commands.put(CommandName.ON_MODERATION_PAGE, new OnModerationCommandPage());
        commands.put(CommandName.ACCEPT_PUBLICATION, new AcceptPublicationCommand());
        commands.put(CommandName.DENY_PUBLICATION, new DenyPublicationCommand());
        commands.put(CommandName.USER_COMMUNITIES, new UserCommunitiesCommandPage());
        commands.put(CommandName.CREATE_COMMUNITY_PAGE, new CreateCommunityCommandPage());
        commands.put(CommandName.CREATE_COMMUNITY, new CreateCommunityCommand());
        commands.put(CommandName.EDIT_COMMUNITY_PAGE, new EditCommunityCommandPage());
        commands.put(CommandName.EDIT_COMMUNITY, new EditCommunityCommand());
        commands.put(CommandName.DELETE_COMMUNITY, new DeleteCommunityCommand());
        commands.put(CommandName.SEARCH, new SearchCommand());
        commands.put(CommandName.SEARCH_USER_COMMUNITY, new SearchUserCommunityCommand());
        commands.put(CommandName.SEARCH_BY_TAG, new SearchByTagCommandPage());
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static CommandProvider getInstance() {
        return instance;
    }

    /**
     * Gets command.
     *
     * @param commandName the command name
     * @return the command
     */
    public Command getCommand(String commandName) {
        CommandName name = CommandName.valueOf(commandName.toUpperCase());
        if (name != null) {
            return commands.get(name);
        }
        return commands.get(CommandName.NO_SUCH_COMMAND);
    }
}
