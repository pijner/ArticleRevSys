/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pijner.articlerevsys.connector;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Prahar
 */
@RequestScoped
@Named("createReview")
public class CreateReview extends Review{
    
    public CreateReview(){
        super("","","","","","","","");
    }

    public CreateReview(String name, String title, String url, String summary, String posneg, String majorPoints, String minorPoints, String recommendation) {
        super(name, title, url, summary, posneg, majorPoints, minorPoints, recommendation);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getSummary() {
        return summary;
    }

    @Override
    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String getPosneg() {
        return posneg;
    }

    @Override
    public void setPosneg(String posneg) {
        this.posneg = posneg;
    }

    @Override
    public String getMajorPoints() {
        return majorPoints;
    }

    @Override
    public void setMajorPoints(String majorPoints) {
        this.majorPoints = majorPoints;
    }

    @Override
    public String getMinorPoints() {
        return minorPoints;
    }

    @Override
    public void setMinorPoints(String minorPoints) {
        this.minorPoints = minorPoints;
    }

    @Override
    public String getRecommendation() {
        return recommendation;
    }

    @Override
    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
}
