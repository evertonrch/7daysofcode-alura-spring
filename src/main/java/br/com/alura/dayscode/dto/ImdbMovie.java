package br.com.alura.dayscode.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class ImdbMovie {

    private String id;
    private String title;
    private String rank;
    private String fullTitle;
    private String year;
    private String image;
    private String crew;
    private String imDbRating;
    private String imDbRatingCount;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getRank() {
        return rank;
    }

    public String getFullTitle() {
        return fullTitle;
    }

    public String getYear() {
        return year;
    }

    public String getImage() {
        return image;
    }

    public String getImDbRating() {
        return imDbRating;
    }

    public String imDbRatingCount() {
        return imDbRatingCount;
    }

    @Override
    public String toString() {
        return "ImdbMovie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", rank='" + rank + '\'' +
                ", fullTitle='" + fullTitle + '\'' +
                ", year='" + year + '\'' +
                ", image=" + image +
                ", imDbRating='" + imDbRating + '\'' +
                ", ImDbRatingCount='" + imDbRatingCount + '\'' +
                '}';
    }
}
