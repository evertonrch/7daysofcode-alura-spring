package br.com.alura.dayscode.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RootImdbMovieJson {

    @JsonProperty("items")
    private ImdbMovie[] imdbMovies;

    private String errorMessage;

    public ImdbMovie[] getImdbMovies() {
        return imdbMovies;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
