package by.parakhnevich.keddit.bean.publication;

import by.parakhnevich.keddit.bean.user.User;

import java.util.Objects;

abstract class Rating {
    protected long id;
    protected User user;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
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
        Rating rating = (Rating) o;
        return id == rating.id && Objects.equals(user, rating.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user);
    }
}
