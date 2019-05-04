package com.example.vollyhomeworkone;

public class Movies {
    String id, name,imageurl,moviename;

    public Movies(String id, String name, String imageurl, String moviename) {
        this.id = id;
        this.name = name;
        this.imageurl = imageurl;
        this.moviename = moviename;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }
}
