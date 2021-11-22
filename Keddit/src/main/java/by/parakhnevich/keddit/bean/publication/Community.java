package by.parakhnevich.keddit.bean.publication;

import by.parakhnevich.keddit.bean.Entity;
import by.parakhnevich.keddit.bean.user.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Community implements Entity {
    private long id;
    String name;
    User user;
    List<User> followers;
    List<Publication> publications;
    File photo;

    public Community() {
        followers = new ArrayList<>();
        publications = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public List<Publication> getPublications() {
        return publications;
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
                ", name='" + name + '\'' +
                ", user=" + user +
                '}';
    }
}
