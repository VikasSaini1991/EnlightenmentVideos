package com.crinoidtechnologies.enlightenmentvideos.models;

public class Videos {
    public String video_id;
    public String title;
    public String description;



    public String getVideoId() {
        return video_id;
    }

    public void setVideoId(String videoId) {
        this.video_id = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
