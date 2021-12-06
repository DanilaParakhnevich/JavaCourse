package by.parakhnevich.keddit.bean.user;

import by.parakhnevich.keddit.bean.Entity;
import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.bean.publication.Publication;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * The entity User.
 * @see Entity
 * @author Danila Parakhnevich
 */
public class User implements Entity {
    private Role role;
    private long id;
    private String mail;
    private String password;
    private String nickname;
    private Timestamp date;
    private File photo;
    private boolean isBanned;
    private List<Community> followingCommunities;
    private List<Community> ownCommunities;
    private List<Publication> publications;

    /**
     * Gets following communities.
     *
     * @return the following communities
     */
    public List<Community> getFollowingCommunities() {
        return followingCommunities;
    }

    /**
     * Gets own communities.
     *
     * @return the own communities
     */
    public List<Community> getOwnCommunities() {
        return ownCommunities;
    }

    /**
     * Gets publications.
     *
     * @return the publications
     */
    public List<Publication> getPublications() {
        return publications;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(Role role) {
        this.role = role;
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
     * Gets mail.
     *
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * Sets mail.
     *
     * @param mail the mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets nickname.
     *
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Sets nickname.
     *
     * @param nickname the nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
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
     * Is banned boolean.
     *
     * @return the boolean
     */
    public boolean isBanned() {
        return isBanned;
    }

    /**
     * Sets banned.
     *
     * @param banned the banned
     */
    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    /**
     * Sets following communities.
     *
     * @param followingCommunities the following communities
     */
    public void setFollowingCommunities(List<Community> followingCommunities) {
        this.followingCommunities = followingCommunities;
    }

    /**
     * Sets own communities.
     *
     * @param ownCommunities the own communities
     */
    public void setOwnCommunities(List<Community> ownCommunities) {
        this.ownCommunities = ownCommunities;
    }

    /**
     * Sets publications.
     *
     * @param publications the publications
     */
    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && role == user.role && Objects.equals(mail, user.mail) && Objects.equals(password, user.password) && Objects.equals(nickname, user.nickname) && Objects.equals(date, user.date) && Objects.equals(photo, user.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, id, mail, password, nickname, date, photo);
    }

    @Override
    public String toString() {
        return "User{" +
                "role=" + role +
                ", id=" + id +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
