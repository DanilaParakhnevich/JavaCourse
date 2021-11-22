package by.parakhnevich.keddit.bean.publication;


import by.parakhnevich.keddit.bean.Entity;
import by.parakhnevich.keddit.bean.user.User;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class    Publication implements Entity {
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

    public Publication() {
        tags = new ArrayList<>();
        ratings = new ArrayList<>();
        comments = new ArrayList<>();
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public Community getCommunityOwner() {
        return communityOwner;
    }

    public void setCommunityOwner(Community communityOwner) {
        this.communityOwner = communityOwner;
    }

    public boolean isOnModeration() {
        return isOnModeration;
    }

    public void setOnModeration(boolean onModeration) {
        isOnModeration = onModeration;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication that = (Publication) o;
        return id == that.id && Objects.equals(communityOwner, that.communityOwner) && Objects.equals(heading, that.heading) && Objects.equals(textContent, that.textContent) && Objects.equals(user, that.user) && Objects.equals(date, that.date) && Objects.equals(tags, that.tags) && Objects.equals(ratings, that.ratings) && Objects.equals(comments, that.comments) && Objects.equals(photo, that.photo);
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
