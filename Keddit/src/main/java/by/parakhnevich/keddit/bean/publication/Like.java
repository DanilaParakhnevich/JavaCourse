package by.parakhnevich.keddit.bean.publication;

/**
 * The entity Like.
 * @see Rating
 * @author Danila Parakhnevich
 */
public class Like extends Rating{

    @Override
    public String toString() {
        return "Like{" +
                "user=" + user +
                '}';
    }
}
