package com.pijner.articlerevsys.connector;

/**
 * This class holds details of reviews in the web application
 *
 * @author Prahar Ijner
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
    protected int numScores;
    protected float averageScore;
    protected int reviewID;

    public Review() {
        this.name = "";
        this.title = "";
        this.url = "";
        this.summary = "";
        this.posneg = "";
        this.majorPoints = "";
        this.minorPoints = "";
        this.recommendation = "";
        this.numScores = 0;
        this.averageScore = 0;
        this.reviewID = -1;
    }

    public Review(String name, String title, String url, String summary, String posneg, String majorPoints, String minorPoints, String recommendation, int numScores, float averageScore, int reviewID) {
        this.name = name;
        this.title = title;
        this.url = url;
        this.summary = summary;
        this.posneg = posneg;
        this.majorPoints = majorPoints;
        this.minorPoints = minorPoints;
        this.recommendation = recommendation;
        this.numScores = numScores;
        this.averageScore = averageScore;
        this.reviewID = reviewID;
    }

    public Review(String name, String title, String url, String summary, String posneg, String majorPoints, String minorPoints, String recommendation) {
        this.name = name;
        this.title = title;
        this.url = url;
        this.summary = summary;
        this.posneg = posneg;
        this.majorPoints = majorPoints;
        this.minorPoints = minorPoints;
        this.recommendation = recommendation;
        this.numScores = 0;
        this.averageScore = 0;
        this.reviewID = -1;
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

    public float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }

    public int getNumScores() {
        return numScores;
    }

    public void setNumScores(int numScores) {
        this.numScores = numScores;
    }

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

}
