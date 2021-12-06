package by.parakhnevich.keddit.bean.publication;

import by.parakhnevich.keddit.bean.Entity;
import by.parakhnevich.keddit.bean.user.User;

import java.util.Objects;

public abstract class Rating implements Entity {
    protected User user;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return Objects.equals(user, rating.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }
}
