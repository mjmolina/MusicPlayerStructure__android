package com.example.mariajosemolina.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.app.ActionBar;
import android.graphics.Color;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

public class ContentList extends AppCompatActivity{

    LinearLayout line1;
    Boolean isArtist;
    Boolean isSong;
    Boolean isAlbum;

    // Remove the duplicate elements of an ArrayList<String> and sort
    // them in alphabetical order
    private void removeDuplicateAndSort(ArrayList<String> input) {
        // A set does not have repeated elements, so we transform the cover to a set
        // to remove the duplicate Strings.
        // After that, we clear our cover and add the set elements to it.
        Set<String> tmp = new HashSet<>();
        tmp.addAll(input);
        input.clear();
        input.addAll(tmp);

        // Sorting our cover alphabetically
        Collections.sort(input, String.CASE_INSENSITIVE_ORDER);
    }

    private void showArtists() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Artists");


        ArrayList<String> artists = getArtists();
        removeDuplicateAndSort(artists);

        for (int i = 0; i < artists.size(); i++) {
            final TextView item = new TextView(ContentList.this);
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            llp.setMargins(50, 40, 0, 40); // llp.setMargins(left, top, right, bottom);
            item.setText(artists.get(i));
            item.setId(i);
            item.setTextSize(20);
            item.setClickable(true);
            item.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                        Intent intent = new Intent(ContentList.this, ArtistView.class);
                        intent.putExtra("artistName", item.getText().toString());
                        startActivity(intent);
                    }
                });


            item.setLayoutParams(llp);

            View horizontalLine = new View(this);
            horizontalLine.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    5
            ));
            horizontalLine.setBackgroundColor(Color.parseColor("#B3B3B3"));

            line1.addView(item);
            line1.addView(horizontalLine);
        }
    }

    private void showSongs(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Songs");

        ArrayList<String> songs = getSongs();

        for (int i=0;i<songs.size();i++) {
            final TextView item = new TextView(ContentList.this);
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            llp.setMargins(50, 40, 0, 40);
            String songName = songs.get(i);
            String idString = songName.split("_")[0];
            final int idInt = Integer.parseInt(idString);


            String songNameClean = songName.split("_")[1];

            item.setText(songNameClean);
            item.setId(idInt*Start.ID_MULTIPLIER+i);
            item.setTextSize(20);
            item.setClickable(true);
            item.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ContentList.this, SimulationPlay.class);
                    intent.putExtra("songName", item.getText().toString());
                    intent.putExtra("idAlbum", idInt);
                    startActivity(intent);

                }
            });
            item.setLayoutParams(llp);// ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            View horizontalLine = new View(this);
            horizontalLine.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    5
            ));
            horizontalLine.setBackgroundColor(Color.parseColor("#B3B3B3"));

            line1.addView(item);
            line1.addView(horizontalLine);
        }
    }
    private void showAlbums() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Albums");


        ArrayList<String> albums = getAlbums();
        removeDuplicateAndSort(albums);

        for (int i = 0; i < albums.size(); i++) {
            final TextView item = new TextView(ContentList.this);
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            llp.setMargins(50, 40, 0, 40); // llp.setMargins(left, top, right, bottom);
            item.setText(albums.get(i));
            item.setId(i);
            item.setTextSize(20);
            item.setClickable(true);
            item.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ContentList.this, AlbumView.class);
                    intent.putExtra("albumName", item.getText().toString());
                    startActivity(intent);
                }
            });
            item.setLayoutParams(llp);// ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            View horizontalLine = new View(this);
            horizontalLine.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    5
            ));
            horizontalLine.setBackgroundColor(Color.parseColor("#B3B3B3"));

            line1.addView(item);
            line1.addView(horizontalLine);
        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putBoolean("isArtist", isArtist);
        savedInstanceState.putBoolean("isSong", isSong);
        savedInstanceState.putBoolean("isAlbum", isAlbum);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_activity);

        line1 = (LinearLayout) findViewById(R.id.line1);

        if (savedInstanceState != null) {
            isArtist = savedInstanceState.getBoolean("isArtist");
            isAlbum = savedInstanceState.getBoolean("isAlbum");
            isSong = savedInstanceState.getBoolean("isSong");
        } else {
            Bundle params = getIntent().getExtras();
            isArtist = params.getBoolean("isArtist");
            isSong = params.getBoolean("isSong");
            isAlbum = params.getBoolean("isAlbum");
        }

        if (isArtist) {
            showArtists();
        } else if (isSong) {
                    showSongs();
                } else if (isAlbum) {
                    showAlbums();
                }
    }
            // Get a cover of all the artists from our data base
            private ArrayList<String> getArtists () {
                ArrayList<String> artists = new ArrayList<String>();
                for (int i = 0; i < Start.db.size(); i++) {
                    artists.add(Start.db.get(i).get(1));
                }
                return artists;
            }
            // Get a cover of all the songs from our data base
            private ArrayList<String> getSongs () {
                ArrayList<String> songs = new ArrayList<String>();
                for (int i = 0; i < Start.db.size(); i++) {
                    ArrayList<String> album = Start.db.get(i);
                    String id = album.get(0);

                    // The first four elements are ID, Artist, Album and Cover
                    for (int j = 4; j < album.size(); j++) {
                        // Here we use a way to store the ID of the album inside the name
                        // of the song, so we can filter easily
                        String song = album.get(j);
                        songs.add(id + "_" + song);
                    }
                }
                return songs;
            }
                // Get a cover of all the albums from our data base
                private ArrayList<String> getAlbums() {
                    ArrayList<String> albums = new ArrayList<String>();
                    for (int i = 0; i < Start.db.size(); i++) {
                        albums.add(Start.db.get(i).get(2));
                    }
                    return albums;
                }
        }