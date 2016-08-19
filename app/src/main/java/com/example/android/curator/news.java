package com.example.android.curator;

/**
 * Created by Danish on 6/13/2016.
 */
public class news {

    private String headline;
    private String description;
    private String link;


    public String getLink() {
        return link;
    }


    news(String h, String d, String e) {
        headline = h;
        description = d;
        link = e;
    }

    news() {
        headline = "";
        description = "";
        link = "";
    }


    public String getHeadline() {
        return headline;
    }

    public String getDescription() {
        return description;
    }

    public void setHeadline(String s) {
        headline = s;
    }

    public void setDescription(String s) {
        description = s;
    }
}
