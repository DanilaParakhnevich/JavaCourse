package by.parakhnevich.keddit.bean.user;

import by.parakhnevich.keddit.bean.Entity;
import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.bean.publication.Publication;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

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


    public Publication getPublication(int index) {
        return publications.get(index);
    }

    public void addPublication(Publication publication) {
        publications.add(publication);
    }

    public void removePublication(int index) {
        publications.remove(index);
    }

    public int countOfPublications() {
        return publications.size();
    }

    public Community getFollowingCommunity(int index) {
        return ownCommunities.get(index);
    }

    public void addFollowingCommunity(Community community) {
        followingCommunities.add(community);
    }

    public void removeFollowingCommunity(int index) {
        followingCommunities.remove(index);
    }

    public int countOfFollowingCommunities() {
        return followingCommunities.size();
    }

    public Community getOwnCommunity(int index) {
        return ownCommunities.get(index);
    }

    public void addOwnCommunity(Community community) {
        ownCommunities.add(community);
    }

    public void removeOwnCommunity(int index) {
        ownCommunities.remove(index);
    }

    public int countOfOwnCommunities() {
        return ownCommunities.size();
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public void setFollowingCommunities(List<Community> followingCommunities) {
        this.followingCommunities = followingCommunities;
    }

    public void setOwnCommunities(List<Community> ownCommunities) {
        this.ownCommunities = ownCommunities;
    }

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
