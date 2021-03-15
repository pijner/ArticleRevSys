package com.pijner.articlerevsys.connector;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * managed bean class that manages the data table entries on the lookup page and
 * handles the scoring function
 *
 * @author Prahar Ijner
 */
@SessionScoped
@Named("lookup")
public class Lookup implements Serializable {

    private ArrayList<Review> availableReviews;
    private Review selectedReview;
    private int selectedReviewScore;

    public Review getSelectedReview() {
        return selectedReview;
    }

    public Lookup() {
        availableReviews = new ArrayList<>();
        selectedReviewScore = 0;
    }

    public Lookup(ArrayList<Review> availableReviews) {
        this.availableReviews = availableReviews;
    }

    public ArrayList<Review> getAvailableReviews() {
        return availableReviews;
    }

    public void setSelectedReview(Review selectedReview) {
        this.selectedReview = selectedReview;
    }

    public void setAvailableReviews(ArrayList<Review> availableReviews) {
        this.availableReviews = availableReviews;
    }

    public int getSelectedReviewScore() {
        return selectedReviewScore;
    }

    public void setSelectedReviewScore(int selectedReviewScore) {
        System.out.println("Score = " + Integer.toString(selectedReviewScore));
        this.selectedReviewScore = selectedReviewScore;
    }

    /**
     * function to submit score of selected review to the database. Before the
     * score is submitted, we fetch the user details and update the user_id
     *
     * @return String containing the redirect link to the lookup page
     */
    public String submitScore() {
        FacesContext context = FacesContext.getCurrentInstance();
        User u = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
        u.updateID();

        DBConnector dbc = new DBConnector();
        dbc.addScore(selectedReview.getReviewID(), u.getUser_id(), selectedReviewScore);
        this.loadReviews();

        return "/lookup.xhtml?faces-redirect=true";
    }

    @PostConstruct
    public void loadReviews() {
        DBConnector dbc = new DBConnector();
        this.availableReviews = dbc.getReviews();
    }

}
