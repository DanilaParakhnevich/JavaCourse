package by.parakhnevich.keddit.dao.mapper;

import by.parakhnevich.keddit.bean.publication.*;
import by.parakhnevich.keddit.bean.user.Role;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.dao.implementation.UserDaoImpl;
import by.parakhnevich.keddit.exception.DaoException;


import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


public class Mapper {
    public Community mapCommunity(ResultSet resultSet) throws DaoException{
        Community community = new Community();
        try {
            community.setId(resultSet.getLong("id"));
            String photo = resultSet.getString("photo");
            if (photo != null) {
                community.setPhoto(new File("D:\\Projects\\JavaCourse\\Keddit\\src\\main\\resources\\photos\\" +
                        photo));
            }
            community.setName(resultSet.getString("name"));
            String userList = resultSet.getString("user");
            if (userList != null) {
                for (String usr : userList.split(",")) {
                    User user = new User();
                    user.setId(Long.parseLong(usr));
                    community.addFollower(user);
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

    public User mapUser(ResultSet resultSet) throws DaoException {
        User user = new User();
        try {
            user.setId(resultSet.getLong("id"));
            user.setDate(Timestamp.valueOf(resultSet.getString("date")));
            user.setMail(resultSet.getString("mail"));
            user.setPassword(resultSet.getString("password"));
            int isBanned = resultSet.getInt("is_banned");
            user.setBanned(isBanned == 1);
            switch (resultSet.getInt("role")) {
                case 1:
                    user.setRole(Role.valueOf("USER"));
                    break;
                case 2:
                    user.setRole(Role.valueOf("MODERATOR"));
                    break;
                default:
                    user.setRole(Role.valueOf("ADMIN"));
                    break;
            }
            if (resultSet.getString("photo") != null) {
                user.setPhoto(new File("D:\\Projects\\JavaCourse\\Keddit\\src\\main\\resources\\photo\\" + resultSet.getString("photo")));
            }
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
        return user;
    }

    public Publication mapPublication(ResultSet resultSet) throws DaoException {
        Publication publication = new Publication();
        UserDaoImpl userDao = new UserDaoImpl();
        try{
            publication.setId(resultSet.getLong("id"));
            User user = new User();
            user.setId(resultSet.getLong("id_user"));
            publication.setUser(user);
            publication.setHeading(resultSet.getString("head"));
            publication.setTextContent("body");
            String photosFromDB = resultSet.getString("photos");
            if (photosFromDB != null) {
                String[] photoNames = photosFromDB.split(";");
                for (String name : photoNames) {
                    publication.addPhoto(new File("D:\\Projects\\JavaCourse\\Keddit\\src\\main\\resources\\photos\\" + name));
                }
            }
            publication.setDate(Timestamp.valueOf(resultSet.getString("date")));
            Community community = new Community();
            community.setId(resultSet.getLong("id_community"));
            publication.setCommunityOwner(community);
            for (String tag : resultSet.getString("tag").split(",")) {
                if (tag != null) {
                    publication.addTag(tag);
                }
            }
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
        return publication;
    }

    public Comment mapComment(ResultSet resultSet) throws DaoException{
        try {
            Comment comment = new Comment();
            comment.setId(resultSet.getLong("id"));
            User user = new User();
            user.setId(resultSet.getLong("id_user"));
            comment.setUser(user);
            comment.setPhoto(new File("D:\\Projects\\JavaCourse\\Keddit\\src\\main\\resources\\photos\\" +
                    resultSet.getString("photo")));
            comment.setDate(Timestamp.valueOf(resultSet.getString("date")));
            comment.setContent(resultSet.getString("content"));
            return comment;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Rating mapRating(ResultSet resultSet) throws DaoException{
        try {
            Rating rating;
            if (resultSet.getBoolean("is_like")) {
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
