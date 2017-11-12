package com.mal.udacity.ahmed.model;

/**
 * Created by Abo Abdel-Badie on 11/23/2016.
 */

public class ReviewModel {

    private String author;
    private String content ;


    public ReviewModel(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

}
