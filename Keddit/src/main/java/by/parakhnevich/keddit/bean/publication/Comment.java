package by.parakhnevich.keddit.bean.publication;

import by.parakhnevich.keddit.bean.SiteBeans;
import by.parakhnevich.keddit.bean.user.User;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;



public class Comment implements SiteBeans {
    private long id;
    User user;
    File photo;
    private String content;
    private List<Like> likes;
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

    public int getCountOfLikes() {
        return likes.size();
    }

    public void addLike(Like like) {
        likes.add(like);
    }

    public void removeLike(int id) {
        likes.remove(id);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id && Objects.equals(user, comment.user) && Objects.equals(photo, comment.photo) && Objects.equals(content, comment.content) && Objects.equals(likes, comment.likes) && Objects.equals(date, comment.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, photo, content, likes, date);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user=" + user +
                ", photo=" + photo.getAbsolutePath() +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                ", date='" + date + '\'' +
                '}';
    }
}

