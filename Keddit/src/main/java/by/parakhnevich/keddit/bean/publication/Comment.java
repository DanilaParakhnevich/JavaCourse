package by.parakhnevich.keddit.bean.publication;

import by.parakhnevich.keddit.bean.Entity;
import by.parakhnevich.keddit.bean.user.User;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;


/**
 * The entity Comment.
 * @see Entity
 * @author Danila Parakhnevich
 */
public class Comment implements Entity {
    private long id;
    private User user;
    private File photo;
    private String content;
    private List<Rating> ratings;
    private Timestamp date;

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets content.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets content.
     *
     * @param content the content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets count of ratings.
     *
     * @return the count of ratings
     */
    public int getCountOfRatings() {
        return ratings.size();
    }

    /**
     * Add rating.
     *
     * @param rating the rating
     */
    public void addRating(Rating rating) {
        ratings.add(rating);
    }

    /**
     * Remove rating.
     *
     * @param id the id
     */
    public void removeRating(int id) {
        ratings.remove(id);
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public Timestamp getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(Timestamp date) {
        this.date = date;
    }

    /**
     * Gets photo.
     *
     * @return the photo
     */
    public File getPhoto() {
        return photo;
    }

    /**
     * Sets photo.
     *
     * @param photo the photo
     */
    public void setPhoto(File photo) {
        this.photo = photo;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Sets ratings.
     *
     * @param ratings the ratings
     */
    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    /**
     * Gets rating.
     *
     * @param index the index
     * @return the rating
     */
    public Rating getRating(int index) {
        return ratings.get(index);
    }

    /**
     * Gets ratings.
     *
     * @return the ratings
     */
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
                ", content='" + content + '\'' +
                ", ratings=" + ratings +
                ", date='" + date + '\'' +
                '}';
    }
}

