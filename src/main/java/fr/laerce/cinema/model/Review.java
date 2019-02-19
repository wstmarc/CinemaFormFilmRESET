package fr.laerce.cinema.model;

import org.springframework.transaction.IllegalTransactionStateException;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Review {
    private static final Map<Integer, Integer[]> transitions;

    static{
        transitions.put(Review.REJECTED, new Integer[]{Review.WAITING_MODERATION});
        transitions.put(Review.DELETED, new Integer[]{Review.PUBLISHED});
        transitions.put(Review.WAITING_MODERATION, new Integer[]{Review.PUBLISHED, Review.WAITING_MODERATION, Review.MUST_BE_MODIFIED});
        transitions.put(Review.PUBLISHED, new Integer[]{Review.WAITING_MODERATION});
        transitions.put(Review.MUST_BE_MODIFIED, new Integer[]{Review.WAITING_MODERATION});
        transitions.put(Review.ABANDONNED, new Integer[]{Review.MUST_BE_MODIFIED});
    }

    public static final int WAITING_MODERATION = 1;
    public static final int PUBLISHED = 2;
    public static final int MUST_BE_MODIFIED = 3;
    public static final int ABANDONNED = 4;
    public static final int REJECTED = 5;
    public static final int DELETED = 6;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "article", nullable = true, length = -1)
    private String article;
    @Basic
    @Column(name = "datte", nullable = false)
    private Timestamp date;
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private int state = Review.WAITING_MODERATION;
    //private int state = Review.MUST_BE_MODIFIED;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }


    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp datte) {
        this.date = datte;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (id != review.id) return false;
        if (article != null ? !article.equals(review.article) : review.article != null) return false;
        if (date != null ? !date.equals(review.date) : review.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (article != null ? article.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    public int getState() {
        return this.state;
    }

    private void transitTo(int target) throws IllegalTransitionStateException {
        if(canTransitTo(target)){
            this.state = target;
        } else {
            throw new IllegalTransitionStateException("Transition non autorisée");
        }
    }

    public void validByModerator() throws IllegalTransitionStateException {
        transitTo(Review.PUBLISHED);
    }

    public void keepForEditByModerator() throws IllegalTransitionStateException {
        transitTo(Review.MUST_BE_MODIFIED);
    }

    public void abandonByUser() throws IllegalTransitionStateException {
        transitTo(Review.ABANDONNED);
    }

    public void rejectByModerator() throws IllegalTransitionStateException {
        transitTo(Review.REJECTED);
    }

    public void deleteByUser() throws IllegalTransitionStateException {
        transitTo(Review.DELETED);
    }

    private boolean canTransitTo(int targetState) {

        return Arrays.asList(transitions.get(targetState)).contains(this.getState());

        /*boolean result;
        switch(targetState){
            case Review.REJECTED:
                result = this.getState() == Review.WAITING_MODERATION;
                break;
            case Review.DELETED:
                result = this.getState() == Review.PUBLISHED;
                break;
            case Review.WAITING_MODERATION:
                result = this.getState() == Review.WAITING_MODERATION
                || this.getState() == Review.MUST_BE_MODIFIED
                || this.getState() == Review.PUBLISHED;
                break;
            case Review.PUBLISHED:
                result = this.getState() == Review.WAITING_MODERATION;
                break;
            case Review.MUST_BE_MODIFIED:
                result = this.getState() == Review.WAITING_MODERATION;
                break;
            case Review.ABANDONNED:
                result = this.getState() == Review.MUST_BE_MODIFIED;
                break;
            default:
                return false;
        }
        return result;*/
    }
}
