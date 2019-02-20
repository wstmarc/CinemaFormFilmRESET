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
    public void etatInitial() {
        Review comment = new Review();
        assertEquals(Review.WAITING_MODERATION, comment.getState());
    }

    @Test
    public void goodTransitionToPublie() {
        Review comment = new Review();
        try {
            comment.validByModerator();
            assertEquals(Review.PUBLISHED, comment.getState());
        } catch (IllegalTransitionStateException e) {
            fail("Transition attendue");
        }
    }

    @Test
    public void badTransitionToPublie() {
        Review comment = new Review();
        try {
            comment.keepForEditByModerator();
            comment.validByModerator();
            fail("Transition non autorisée");
        } catch (IllegalTransitionStateException e) {
            assertEquals(Review.MUST_BE_MODIFIED, comment.getState());
        }
    }

    @Test
    public void goodTransitionToAbandonne() {
        Review comment = new Review();
        try {
            comment.keepForEditByModerator();
            comment.abandonByUser();
            assertEquals(Review.ABANDONED, comment.getState());
        } catch (IllegalTransitionStateException e) {
            fail("Transition attendue");
        }
    }

    @Test
    public void badTransitionToAbandoned() {
        Review comment = new Review();
        try {
            comment.validByModerator();
            comment.abandonByUser();
            fail("Transition vers Abandonne non autorisée");
        } catch (IllegalTransitionStateException e) {
            assertEquals(Review.PUBLISHED, comment.getState());
        }
    }

    @Test
    public void badTransitionToAbandoned2() {
        Review comment = new Review();
        try {
            comment.rejectByModerator();
            comment.abandonByUser();
            fail("Transition vers Abandonne non autorisée");
        } catch (IllegalTransitionStateException e) {
            assertEquals(Review.REJECTED, comment.getState());
        }
    }

    @Test
    public void goodTransitionReject() {
        Review comment = new Review();
        try {
            comment.rejectByModerator();
            assertEquals(Review.REJECTED, comment.getState());
        } catch (IllegalTransitionStateException e) {
            fail("Transition attendue");
        }
    }

    @Test
    public void badTransitionReject() {
        Review comment = new Review();
        try {
            comment.validByModerator();
            comment.rejectByModerator();
            fail("Transition vers Rejected non autorisée");
        } catch (IllegalTransitionStateException e) {
            assertEquals(Review.PUBLISHED, comment.getState());
        }
    }


    @Test
    public void goodTransitionEditByUserFromPublished() {
        Review comment = new Review();
        try {
            comment.validByModerator();
            comment.editByUser();
            assertEquals(Review.WAITING_MODERATION, comment.getState());
        } catch (IllegalTransitionStateException e) {
            fail("Transition attendue");
        }
    }


    @Test
    public void goodTransitionEditByUserFromWAITING_MODERATION() {
        Review comment = new Review();
        try {
            assertEquals(Review.WAITING_MODERATION, comment.getState());
            comment.editByUser();
            assertEquals(Review.WAITING_MODERATION, comment.getState());
        } catch (IllegalTransitionStateException e) {
            fail("Aucune transition attendue");
        }
    }


    @Test
    public void goodTransitionEditByUserFromMUST_BE_MODIFIED() {
        Review comment = new Review();
        try {
            comment.keepForEditByModerator();
            assertEquals(Review.MUST_BE_MODIFIED, comment.getState());
            comment.editByUser();
            assertEquals(Review.WAITING_MODERATION, comment.getState());
        } catch (IllegalTransitionStateException e) {
            fail("Transition attendue");
        }
    }

    @Test
    public void badTransitionEditByUserFromABANDONED() {
        Review comment = new Review();
        try {
            comment.keepForEditByModerator();
            comment.abandonByUser();
            comment.editByUser();
            fail("Transition non permise attendue");
        } catch (IllegalTransitionStateException e) {
            assertEquals(Review.ABANDONED, comment.getState());
        }
    }

}