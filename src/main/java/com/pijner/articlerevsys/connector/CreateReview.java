package com.pijner.articlerevsys.connector;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 * Managed bean class to handle creation of new reviews and writing the to DB
 * The class inherits from Review.class
 *
 * @author Prahar Ijner
 */
@RequestScoped
@Named("createReview")
public class CreateReview extends Review {

    private String errorMessage;

    public CreateReview() {
        super();
    }

    public CreateReview(String name, String title, String url, String summary, String posneg, String majorPoints, String minorPoints, String recommendation) {
        super(name, title, url, summary, posneg, majorPoints, minorPoints, recommendation);
    }

    /**
     * post constructor function call to load the user's name
     */
    @PostConstruct
    public void getUserName() {
        FacesContext context = FacesContext.getCurrentInstance();
        User u = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
        this.name = u.getName();
    }

    /**
     * function to check if all the fields are filled with some information
     *
     * @return String "pass" if everything is OK, otherwise string with error
     */
    private String checkFields() {
        if (this.name == null || this.name.isEmpty()) {
            return "Name field is empty";
        }
        if (this.title == null || this.title.isEmpty()) {
            return "Title field is empty";
        }
        if (this.url == null || this.url.isEmpty()) {
            return "URL field is empty";
        }
        if (this.summary == null || this.summary.isEmpty()) {
            return "Summary field is empty";
        }
        if (this.posneg == null || this.posneg.isEmpty()) {
            return "Positive/Negative field is empty";
        }
        if (this.majorPoints == null || this.majorPoints.isEmpty()) {
            return "Major points field is empty";
        }
        if (this.minorPoints == null || this.minorPoints.isEmpty()) {
            return "Minor points field is empty";
        }
        if (this.recommendation == null || this.recommendation.isEmpty()) {
            return "Recommendation field not selected";
        }

        return "pass";
    }

    /**
     * function to save the review to the database
     */
    public void saveReview() {
        this.getUserName();
        String status = this.checkFields();
        if (!("pass".equals(status))) {
            this.errorMessage = status;
        } else {
            DBConnector dbc = new DBConnector();
            status = dbc.writeReview(this);
            if (!("pass".equals(status))) {
                this.errorMessage = status;
            } else {
                this.errorMessage = "Submitted!";
                FacesContext context = FacesContext.getCurrentInstance();
                Lookup l = context.getApplication().evaluateExpressionGet(context, "#{lookup}", Lookup.class);
                l.loadReviews();
            }
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Status", this.errorMessage);
        PrimeFaces.current().dialog().showMessageDynamic(message);
    }

}
