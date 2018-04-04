package com.example.mariajosemolina.myapplication;

import java.util.ArrayList;

public class Album {
    int id;
    String artistName;
    String albumName;
    String albumCover;
    ArrayList<String> songList;

    public Album(int id, String artistName, String albumName, String albumCover, ArrayList<String> songList) {
        this.id = id;
        this.artistName = artistName;
        this.albumName = albumName;
        this.albumCover = albumCover;
        this.songList = songList;
    }
}
