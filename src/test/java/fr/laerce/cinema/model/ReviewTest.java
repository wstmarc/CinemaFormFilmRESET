package fr.laerce.cinema.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReviewTest {

    @Test
    public void setArticle() {
        Review comment = new Review();
        comment.setArticle("test commentaire");
        assertEquals("test commentaire", comment.getArticle());
    }

    @Test
    public void initialState() {
        Review comment = new Review();
        assertEquals(Review.WAITING_MODERATION, comment.getState());
    }

    @Test
    public void goodTransitionToPublie() {
        Review comment = new Review();
        try{
            comment.validByModerator();
            assertEquals(Review.PUBLISHED, comment.getState());
        } catch (IllegalTransitionStateException e){
            fail("Transition vers Publié non autorisée");
        }
    }

    @Test
    public void badTransitionToPublie() {
        Review comment = new Review();
        try{
            comment.keepForEditByModerator();
            //assertEquals(Review.MUST_BE_MODIFIED, comment.getState());
            comment.validByModerator();
            fail("Transition vers Publié non autorisée");
        } catch (IllegalTransitionStateException e){
            assertEquals(Review.MUST_BE_MODIFIED, comment.getState());
        }
    }

    @Test
    public void goodTransitionToAbandonne() {
        Review comment = new Review();
        try{
            comment.keepForEditByModerator();
//            comment.abandonnedByUser();
            comment.abandonByUser();
            assertEquals(Review.ABANDONNED, comment.getState());
        } catch (IllegalTransitionStateException e){
            fail("Transition vers Abandonné non autorisée");
        }
    }

    @Test
    public void badTransitionToAbandonne() {
        Review comment = new Review();
        try{
            comment.validByModerator();
            comment.deleteByUser();
//            comment.abandonnedByUser();
            comment.abandonByUser();
            fail("Transition vers Abandonné non autorisée");
        } catch (IllegalTransitionStateException e){
            assertEquals(Review.DELETED, comment.getState());
        }
    }

    @Test
    public void goodTransitionToReject() {
        Review comment = new Review();
        try{
//            comment.rejectedByModerator();
            comment.rejectByModerator();
            assertEquals(Review.REJECTED, comment.getState());
        } catch (IllegalTransitionStateException e){
            fail("Transition vers Abandonné non autorisée");
        }
    }

    @Test
    public void goodTransitionToDelete() {
        Review comment = new Review();
        try{
            comment.validByModerator();
            comment.deleteByUser();
            assertEquals(Review.DELETED, comment.getState());
        } catch (IllegalTransitionStateException e){
            fail("Transition vers Abandonné non autorisée");
        }
    }
}