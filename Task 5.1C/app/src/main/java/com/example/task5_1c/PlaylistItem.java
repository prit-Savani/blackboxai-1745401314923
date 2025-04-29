package com.example.task5_1c;

public class PlaylistItem {
    private int id;
    private String title;
    private String videoUrl;

    public PlaylistItem(int id, String title, String videoUrl) {
        this.id = id;
        this.title = title;
        this.videoUrl = videoUrl;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getVideoUrl() {
        return videoUrl;
    }
}
