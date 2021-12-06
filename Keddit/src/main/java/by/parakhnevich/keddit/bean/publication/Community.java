package by.parakhnevich.keddit.bean.publication;

import by.parakhnevich.keddit.bean.Entity;
import by.parakhnevich.keddit.bean.user.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The entity Community.
 * @see Entity
 * @author Danila Parakhnevich
 */
public class Community implements Entity {
    private long id;
    private String name;
    private User user;
    private List<User> followers;
    private List<Publication> publications;
    private File photo;

    /**
     * Instantiates a new Community.
     */
    public Community() {
        followers = new ArrayList<>();
        publications = new ArrayList<>();
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
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
     * Sets publications.
     *
     * @param publications the publications
     */
    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }

    /**
     * Sets followers.
     *
     * @param followers the followers
     */
    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    /**
     * Gets followers.
     *
     * @return the followers
     */
    public List<User> getFollowers() {
        return followers;
    }

    /**
     * Gets publications.
     *
     * @return the publications
     */
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
