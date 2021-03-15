package com.pijner.articlerevsys.connector;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * This enum just exists to hold the strings for accept, reject, major revision, minor revision
 * @author Prahar
 */
@ApplicationScoped
@Named("status")
public enum ReviewStatus {
    ACCEPT("Accept"), REJECT("Reject"), MAJOR_REV("Major revision"), MINOR_REV("Minor revision");
    
    private final String status;
    
    private ReviewStatus(String statusValue){
        this.status = statusValue;
    }

    public String getStatus() {
        return status;
    }
}
