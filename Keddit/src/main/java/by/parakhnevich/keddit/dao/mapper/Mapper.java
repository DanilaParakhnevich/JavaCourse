package by.parakhnevich.keddit.dao.mapper;

import by.parakhnevich.keddit.bean.publication.*;
import by.parakhnevich.keddit.bean.user.Role;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.dao.exception.DaoException;


import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


/**
 * The class Mapper that map Entity objects instead of ResultSet.
 */
public final class Mapper {
    private static final String PATH_TO_PHOTOS = "D:\\Projects\\JavaCourse\\Keddit\\src\\main\\webapp\\photos\\";

    /**
     * Map community.
     *
     * @param resultSet the result set
     * @return the community
     * @throws DaoException the dao exception
     */
    public Community mapCommunity(ResultSet resultSet) throws DaoException{
        Community community = new Community();
        try {
            community.setId(resultSet.getLong("id"));
            String photo = resultSet.getString("photo");
            if (photo != null) {
                community.setPhoto(new File(PATH_TO_PHOTOS +
                        photo));
            } else {
                community.setPhoto(null);
            }
            community.setName(resultSet.getString("name"));
            String userList = resultSet.getString("user");
            if (userList != null) {
                for (String usr : userList.split(",")) {
                    User user = new User();
                    user.setId(Long.parseLong(usr));
                    community.getFollowers().add(user);
                }
            }
            User user = new User();
            user.setId(resultSet.getLong("id_user"));
            community.setUser(user);
            return community;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Map user.
     *
     * @param resultSet the result set
     * @return the user
     * @throws DaoException the dao exception
     */
    public User mapUser(ResultSet resultSet) throws DaoException {
        User user = new User();
        try {
            user.setNickname(resultSet.getString("nickname"));
            user.setId(resultSet.getLong("id"));
            user.setDate(Timestamp.valueOf(resultSet.getString("date")));
            user.setMail(resultSet.getString("mail"));
            user.setPassword(resultSet.getString("password"));
            int isBanned = resultSet.getInt("is_banned");
            user.setBanned(isBanned == 1);
            switch (resultSet.getInt("role")) {
                case 1 -> user.setRole(Role.valueOf("USER"));
                case 2 -> user.setRole(Role.valueOf("MODERATOR"));
                default -> user.setRole(Role.valueOf("ADMIN"));
            }
            if (resultSet.getString("photo") != null) {
                user.setPhoto(new File(PATH_TO_PHOTOS + resultSet.getString("photo")));
            }
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
        return user;
    }

    /**
     * Map publication.
     *
     * @param resultSet the result set
     * @return the publication
     * @throws DaoException the dao exception
     */
    public Publication mapPublication(ResultSet resultSet) throws DaoException {
        Publication publication = new Publication();
        try{
            publication.setId(resultSet.getLong("id"));
            User user = new User();
            user.setId(resultSet.getLong("id_user"));
            publication.setUser(user);
            publication.setHeading(resultSet.getString("head"));
            publication.setTextContent(resultSet.getString("body"));
            if (resultSet.getString("photos") != null) {
                publication.setPhoto(new File(PATH_TO_PHOTOS + resultSet.getString("photos")));
            }
            publication.setDate(Timestamp.valueOf(resultSet.getString("date")));
            Community community = new Community();
            long id = resultSet.getLong("id_community");
            if (id == 0) {
                publication.setCommunityOwner(null);
            }
            else {
                community.setId(id);
                publication.setCommunityOwner(community);
            }
            for (String tag : resultSet.getString("tag").split(",")) {
                if (tag != null) {
                    publication.addTag(tag);
                }
            }
            publication.setOnModeration(resultSet.getInt("is_on_moderation") == 1);
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
        return publication;
    }

    /**
     * Map comment.
     *
     * @param resultSet the result set
     * @return the comment
     * @throws DaoException the dao exception
     */
    public Comment mapComment(ResultSet resultSet) throws DaoException{
        try {
            Comment comment = new Comment();
            comment.setId(resultSet.getLong("id"));
            User user = new User();
            user.setId(resultSet.getLong("id_user"));
            comment.setUser(user);
            if (resultSet.getString("photo") == null) {
                comment.setPhoto(null);
            } else {
                comment.setPhoto(new File(PATH_TO_PHOTOS +
                        resultSet.getString("photo")));
            }
            comment.setDate(Timestamp.valueOf(resultSet.getString("date")));
            comment.setContent(resultSet.getString("content"));
            return comment;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Map rating.
     *
     * @param resultSet the result set
     * @return the rating
     * @throws DaoException the dao exception
     */
    public Rating mapRating(ResultSet resultSet) throws DaoException{
        try {
            Rating rating;
            if (resultSet.getInt("is_like") == 1) {
                rating = new Like();
            } else {
                rating = new Dislike();
            }
            User user = new User();
            user.setId(resultSet.getLong("id_user"));
            rating.setUser(user);
            return rating;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
