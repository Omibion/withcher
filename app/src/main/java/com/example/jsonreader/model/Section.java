package com.example.jsonreader.model;

import java.util.ArrayList;
import java.util.List;

public class Section {
    private String title;
    private String subtitle;
    private String image;
    private List<Entry> entries;

    public Section(String subtitle, String title, String image) {
        this.subtitle = subtitle;
        this.title = title;
        this.image = image;

        entries = new ArrayList<>();
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Section{" +
                "title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", image='" + image + '\'' +
                ", entries=" + entries +
                '}';
    }
}
