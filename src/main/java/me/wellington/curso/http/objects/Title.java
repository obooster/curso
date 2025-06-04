package me.wellington.curso.http.objects;

import com.google.gson.annotations.SerializedName;

public class Title {
    @SerializedName("Title")
    private String title;
    @SerializedName("Year")
    private int year;

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String toString() {
        return "title='" + title + "'" + ", year=" + year;
    }

}
