package by.parakhnevich.keddit.bean.publication;


import by.parakhnevich.keddit.bean.Entity;
import by.parakhnevich.keddit.bean.user.User;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The entity Publication.
 * @see Entity
 * @author Danila Parakhnevich
 */
public class Publication implements Entity {
    private long id;
    private String heading;
    private String textContent;
    private User user;
    private Timestamp date;
    private Community communityOwner;
    private List<String> tags;
    private List<Rating> ratings;
    private List<Comment> comments;
    private File photo;
    private boolean isOnModeration;

    /**
     * Instantiates a new Publication.
     */
    public Publication() {
        tags = new ArrayList<>();
        ratings = new ArrayList<>();
        comments = new ArrayList<>();
    }

    /**
     * Gets heading.
     *
     * @return the heading
     */
    public String getHeading() {
        return heading;
    }

    /**
     * Sets heading.
     *
     * @param heading the heading
     */
    public void setHeading(String heading) {
        this.heading = heading;
    }

    /**
     * Gets text content.
     *
     * @return the text content
     */
    public String getTextContent() {
        return textContent;
    }

    /**
     * Sets text content.
     *
     * @param textContent the text content
     */
    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

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
     * Add tag.
     *
     * @param tag the tag
     */
    public void addTag(String tag) {
        tags.add(tag);
    }

    /**
     * Gets community owner.
     *
     * @return the community owner
     */
    public Community getCommunityOwner() {
        return communityOwner;
    }

    /**
     * Sets community owner.
     *
     * @param communityOwner the community owner
     */
    public void setCommunityOwner(Community communityOwner) {
        this.communityOwner = communityOwner;
    }

    /**
     * Is on moderation boolean.
     *
     * @return the boolean
     */
    public boolean isOnModeration() {
        return isOnModeration;
    }

    /**
     * Sets on moderation.
     *
     * @param onModeration the on moderation
     */
    public void setOnModeration(boolean onModeration) {
        isOnModeration = onModeration;
    }

    /**
     * Sets comments.
     *
     * @param comments the comments
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
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
     * Gets tags.
     *
     * @return the tags
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * Sets tags.
     *
     * @param tags the tags
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * Gets ratings.
     *
     * @return the ratings
     */
    public List<Rating> getRatings() {
        return ratings;
    }

    /**
     * Gets comments.
     *
     * @return the comments
     */
    public List<Comment> getComments() {
        return comments;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication that = (Publication) o;
        return id == that.id && Objects.equals(communityOwner, that.communityOwner) && Objects.equals(heading, that.heading) && Objects.equals(textContent, that.textContent) && Objects.equals(date, that.date) && Objects.equals(tags, that.tags) && Objects.equals(photo, that.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, heading, textContent, user, date, communityOwner, tags, ratings, comments, photo);
    }

    @Override
    public String toString() {
        return "Publication{" +
                "id=" + id +
                ", heading='" + heading + '\'' +
                ", textContent='" + textContent + '\'' +
                ", user=" + user +
                ", date='" + date + '\'' +
                ", communityOwner='" + communityOwner.toString() + '\'' +
                ", tags='" + tags.toString() + '\'' +
                '}';
    }
}
