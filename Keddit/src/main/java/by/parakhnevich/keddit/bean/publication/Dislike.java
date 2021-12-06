package by.parakhnevich.keddit.bean.publication;

/**
 * The entity Dislike.
 * @see Rating
 * @author Danila Parakhnevich
 */
public class Dislike extends Rating{

    @Override
    public String toString() {
        return "Dislike{" +
                "user=" + user +
                '}';
    }
}
