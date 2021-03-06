package by.parakhnevich.keddit.controller.command;

/**
 * The enum CommandName that contains names of all
 * the commands for Controller.
 * @see by.parakhnevich.keddit.controller.command.CommandProvider
 * @author Danila Parakhnevich
 */
public enum CommandName {
    NO_SUCH_COMMAND,

    LOGOUT,
    LOGIN,
    SIGN_UP,
    REGISTRATION_PAGE,
    LOGIN_PAGE,


    //GET
    CREATE_PUBLICATION_BY_USER_PAGE,
    CREATE_PUBLICATION_BY_USER,
    CREATE_PUBLICATION_BY_COMMUNITY_PAGE,
    CREATE_PUBLICATION_BY_COMMUNITY,
    PUBLICATIONS,
    MY_COMMUNITIES,
    FOLLOWERS,
    USER_PAGE,
    PUBLICATION_PAGE,
    ABOUT,
    COMMUNITY_PAGE,
    SEARCH_PAGE,
    SUBSCRIBE_COMMUNITY,
    SEARCH_FOLLOWER,
    SET_LIKE_PUBLICATION,
    SET_DISLIKE_PUBLICATION,
    SET_LIKE_COMMENT,
    SET_DISLIKE_COMMENT,
    EDIT_PUBLICATION_PAGE,
    SEARCH_USER_COMMUNITY,
    EDIT_PUBLICATION,
    DELETE_USER,
    DELETE_COMMENT,
    DELETE_PUBLICATION,
    CREATE_COMMENT,
    EDIT_USER_PAGE,
    EDIT_USER,
    BLOCK_USER,
    EDIT_ROLE_PAGE,
    EDIT_ROLE,
    CHANGE_LANG,
    ON_MODERATION_PAGE,
    ACCEPT_PUBLICATION,
    DENY_PUBLICATION,
    USER_COMMUNITIES,//!!
    SEARCH_USER_COMMUNITIES,
    CREATE_COMMUNITY_PAGE,
    CREATE_COMMUNITY,
    EDIT_COMMUNITY_PAGE,
    EDIT_COMMUNITY,
    DELETE_COMMUNITY,
    SEARCH,
    SEARCH_BY_TAG,
    //FOR_MODERATOR_AND_ADMIN
    PUBLICATIONS_ON_MODERATION
}
