package by.parakhnevich.kedid.bean;

import by.parakhnevich.kedid.bean.publication.Publication;
import by.parakhnevich.kedid.bean.user.User;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class Community {
    private long id;
    User user;
    List<User> followers;
    List<Publication> publications;
    File photo;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public Publication getPublication(int id) {
        return publications.get(id);
    }

    public void addPublication(Publication publication) {
        publications.add(publication);
    }

    public void removePublication(int id) {
        publications.remove(id);
    }

    public int countOfPublications() {
        return publications.size();
    }

    public User getFollower(int id) {
        return followers.get(id);
    }

    public void addFollower(User user) {
        followers.add(user);
    }

    public void removeFollower(int id) {
        followers.remove(id);
    }

    public int countOfFollowers() {
        return followers.size();
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
        Community community = (Community) o;
        return id == community.id && Objects.equals(user, community.user) && Objects.equals(followers, community.followers) && Objects.equals(publications, community.publications) && Objects.equals(photo, community.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, followers, publications, photo);
    }

    @Override
    public String toString() {
        return "Community{" +
                "id=" + id +
                ", user=" + user +
                ", followers=" + followers +
                ", publications=" + publications +
                ", photo=" + photo.getAbsolutePath() +
                '}';
    }
}
