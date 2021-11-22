package by.parakhnevich.keddit.bean.publication;

import by.parakhnevich.keddit.bean.Entity;
import by.parakhnevich.keddit.bean.user.User;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;



public class Comment implements Entity {
    private long id;
    User user;
    File photo;
    private String content;
    private List<Rating> ratings;
    private Timestamp date;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCountOfRatings() {
        return ratings.size();
    }

    public void addRating(Rating rating) {
        ratings.add(rating);
    }

    public void removeRating(int id) {
        ratings.remove(id);
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public Rating getRating(int index) {
        return ratings.get(index);
    }
    public List<Rating> getRatings() {
        return ratings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id && Objects.equals(user, comment.user) && Objects.equals(photo, comment.photo) && Objects.equals(content, comment.content) && Objects.equals(ratings, comment.ratings) && Objects.equals(date, comment.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, photo, content, ratings, date);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user=" + user +
                ", photo=" + photo.getAbsolutePath() +
                ", content='" + content + '\'' +
                ", ratings=" + ratings +
                ", date='" + date + '\'' +
                '}';
    }
}

