package ru.javaprojects.photoappwebclient.model;

public class Album {
    private String albumId;
    private String userId;
    private String albumTitle;
    private String albumDescription;
    private String albumUrl;

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getAlbumDescription() {
        return albumDescription;
    }

    public void setAlbumDescription(String albumDescription) {
        this.albumDescription = albumDescription;
    }

    public String getAlbumUrl() {
        return albumUrl;
    }

    public void setAlbumUrl(String albumUrl) {
        this.albumUrl = albumUrl;
    }
}
