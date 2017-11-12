package com.mal.udacity.ahmed.model;

/**
 * Created by Abo Abdel-Badie on 11/23/2016.
 */

public class TrailerModel {
    private String key;
    private String name;
    private String site;
    private String size;
    private String type;

    public TrailerModel(String key, String name, String site, String size, String type) {
        this.key = key;
        this.name = name;
        this.site = site;
        this.size = size;
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }



}
