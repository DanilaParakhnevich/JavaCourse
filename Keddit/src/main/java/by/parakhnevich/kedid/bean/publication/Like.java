package by.parakhnevich.kedid.bean.publication;

import by.parakhnevich.kedid.bean.user.User;

import java.util.Objects;

public class Like extends Rating{

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", user=" + user +
                '}';
    }
}
