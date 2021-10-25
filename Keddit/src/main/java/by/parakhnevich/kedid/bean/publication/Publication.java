package by.parakhnevich.kedid.bean.publication;


import by.parakhnevich.kedid.bean.user.User;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class Publication {
    private long id;
    private String heading;
    private String textContent;
    private User user;
    private String date;
    private String tag;
    private List<Like> likes;
    private List<Comment> comments;
    private List<File> photos;


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

    public Like getLike(int id) {
        return likes.get(id);
    }

    public void addLike(Like like) {
        likes.add(like);
    }

    public void deleteLike(int id) {
        likes.remove(id);
    }

    public int getCountOfLikes() {
        return likes.size();
    }

    public void removeLike(Like like) {
        likes.remove(like);
    }

    public int getCountOfPhotos() {
        return photos.size();
    }

    public void addPhoto(File photo) {
        photos.add(photo);
    }

    public void removePhoto(int id) {
        photos.remove(id);
    }

    public File getPhoto(int id) {
        return photos.get(id);
    }

    public int getCountOfComments() {
        return comments.size();
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void removeComment(int id) {
        comments.remove(id);
    }

    public Comment getComment(int id) {
        return comments.get(id);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication that = (Publication) o;
        return id == that.id && Objects.equals(heading, that.heading) && Objects.equals(textContent, that.textContent) && Objects.equals(user, that.user) && Objects.equals(date, that.date) && Objects.equals(tag, that.tag) && Objects.equals(likes, that.likes) && Objects.equals(comments, that.comments) && Objects.equals(photos, that.photos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, heading, textContent, user, date, tag, likes, comments, photos);
    }

    @Override
    public String toString() {
        return "Publication{" +
                "id=" + id +
                ", heading='" + heading + '\'' +
                ", textContent='" + textContent + '\'' +
                ", user=" + user +
                ", time='" + date + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
