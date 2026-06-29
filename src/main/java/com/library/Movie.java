package com.library;

import java.io.Serializable;

public class Movie implements Serializable {
    private String id;
    private String title;
    private String genre;
    private double imdbRating;
    private String description;
    private String personalImpressions;
    private int personalRating;
    private String videoUrl;
    private boolean isCustom;

    public Movie(String id, String title, String genre, double imdbRating,
                 String description, String personalImpressions, int personalRating,
                 String videoUrl, boolean isCustom) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.imdbRating = imdbRating;
        this.description = description;
        this.personalImpressions = personalImpressions;
        this.personalRating = personalRating;
        this.videoUrl = videoUrl;
        this.isCustom = isCustom;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public double getImdbRating() { return imdbRating; }
    public void setImdbRating(double imdbRating) { this.imdbRating = imdbRating; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPersonalImpressions() { return personalImpressions; }
    public void setPersonalImpressions(String personalImpressions) { this.personalImpressions = personalImpressions; }

    public int getPersonalRating() { return personalRating; }
    public void setPersonalRating(int personalRating) { this.personalRating = personalRating; }

    public String getVideoUrl() { return videoUrl; }
    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }

    public boolean isCustom() { return isCustom; }
    public void setCustom(boolean custom) { isCustom = custom; }

    @Override
    public String toString() {
        return title + " (" + genre + ") ★" + imdbRating;
    }
}