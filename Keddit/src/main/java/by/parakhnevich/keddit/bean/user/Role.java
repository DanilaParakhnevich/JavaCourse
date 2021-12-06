package by.parakhnevich.keddit.bean.user;

import by.parakhnevich.keddit.bean.Entity;

/**
 * The enum Role for entity User.
 * @see by.parakhnevich.keddit.bean.user.User
 * @author Danila Parakhnevich
 */
public enum Role implements Entity {
    ADMIN,
    MODERATOR,
    USER;
}
