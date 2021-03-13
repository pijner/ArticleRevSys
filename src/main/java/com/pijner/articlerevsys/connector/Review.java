/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pijner.articlerevsys.connector;

/**
 *
 * @author Prahar
 */
public class Review {
    protected String name;
    protected String title;
    protected String url;
    protected String summary;
    protected String posneg;
    protected String majorPoints;
    protected String minorPoints;
    protected String recommendation;

    public Review(String name, String title, String url, String summary, String posneg, String majorPoints, String minorPoints, String recommendation) {
        this.name = name;
        this.title = title;
        this.url = url;
        this.summary = summary;
        this.posneg = posneg;
        this.majorPoints = majorPoints;
        this.minorPoints = minorPoints;
        this.recommendation = recommendation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPosneg() {
        return posneg;
    }

    public void setPosneg(String posneg) {
        this.posneg = posneg;
    }

    public String getMajorPoints() {
        return majorPoints;
    }

    public void setMajorPoints(String majorPoints) {
        this.majorPoints = majorPoints;
    }

    public String getMinorPoints() {
        return minorPoints;
    }

    public void setMinorPoints(String minorPoints) {
        this.minorPoints = minorPoints;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
    
    
}
