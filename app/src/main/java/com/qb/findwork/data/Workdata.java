package com.qb.findwork.data;

import java.io.Serializable;

public class Workdata implements Serializable{
    private String title;
    private String man;
    private int photoId;

    public Workdata (String name, String man, int photoId) {
        this.title=name;
        this.man=man;
        this.photoId=photoId;
    }

    public void setMan(String man) {
        this.man = man;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getMan() {
        return man;
    }

    public int getPhotoId() {
        return photoId;
    }

    public String getTitle() {
        return title;
    }
}
