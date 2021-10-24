package by.parakhnevich.kedid.bean.user;

import by.parakhnevich.kedid.bean.Community;
import by.parakhnevich.kedid.bean.publication.Publication;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private Role role;
    private long id;
    private String mail;
    private String password;
    private String nickname;
    private String date;
    public File photo;
    public static final List<Community> followingCommunities = new ArrayList<>();
    public static final List<Community> ownCommunities = new ArrayList<>();
    private static final List<Publication> publications = new ArrayList<>();


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

    public Community getFollowingCommunity(int id) {
        return ownCommunities.get(id);
    }

    public void addFollowingCommunity(Community community) {
        followingCommunities.add(community);
    }

    public void removeFollowingCommunity(int id) {
        followingCommunities.remove(id);
    }

    public int countOfFollowingCommunities() {
        return followingCommunities.size();
    }

    public Community getOwnCommunity(int id) {
        return ownCommunities.get(id);
    }

    public void addOwnCommunity(Community community) {
        ownCommunities.add(community);
    }

    public void removeOwnCommunity(int id) {
        ownCommunities.remove(id);
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
                ", photo=" + photo.getAbsolutePath() +
                '}';
    }
}