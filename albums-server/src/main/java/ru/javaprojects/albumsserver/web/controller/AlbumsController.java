package ru.javaprojects.albumsserver.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaprojects.albumsserver.web.model.Album;

import java.util.List;

@RestController
@RequestMapping(value = "/albums", produces = MediaType.APPLICATION_JSON_VALUE)
public class AlbumsController {

    @GetMapping
    public List<Album> getAlbums() {
        Album album1 = new Album();
        album1.setAlbumId("albumIdHere");
        album1.setUserId("1");
        album1.setAlbumTitle("Album 1 title");
        album1.setAlbumDescription("Album 1 description");
        album1.setAlbumUrl("Album 1 url");

        Album album2 = new Album();
        album2.setAlbumId("albumIdHere");
        album2.setUserId("2");
        album2.setAlbumTitle("Album 2 title");
        album2.setAlbumDescription("Album 2 description");
        album2.setAlbumUrl("Album 2 url");

        return List.of(album1, album2);
    }
}
