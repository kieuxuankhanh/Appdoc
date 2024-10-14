package com.example.appdocsach.object;

import java.io.Serializable;

public class Author implements Serializable {
    private String PicUrl;
    private String Author;
    public Author(){}

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }
}
