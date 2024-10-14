package com.example.appdocsach.object;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class anime_re implements Serializable {
    private String Title;
    private String Description;
    private String Poster;
    private String Time;
    private int Imdb;
    private ArrayList<String> Genre;
    private ArrayList<Author> authors;

    public anime_re(){}

    public String getTitle() {
        return Title;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getImdb() {
        return Imdb;
    }

    public void setImdb(int imdb) {
        Imdb = imdb;
    }

    public ArrayList<String> getGenre() {
        return Genre;
    }

    public void setGenre(ArrayList<String> genre) {
        Genre = genre;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }
}
